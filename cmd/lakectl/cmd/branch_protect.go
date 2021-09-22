package cmd

import (
	"github.com/spf13/cobra"
	"github.com/treeverse/lakefs/pkg/api"
)

const (
	branchProtectAddCmdArgs = 2
)

var branchProtectCmd = &cobra.Command{
	Use:   "branch-protect",
	Short: "Create and manage branch protection rules",
	Long:  "Define branch protection rules to prevent direct changes. Changes to protected branches can only be done by merging from other branches.",
}
var branchProtectListCmd = &cobra.Command{
	Use:     "list <repo uri>",
	Short:   "List all branch protection rules",
	Example: "list lakefs://<repository>",
	Args:    cobra.ExactArgs(1),
	Run: func(cmd *cobra.Command, args []string) {
		client := getClient()
		u := MustParseRepoURI("repo", args[0])
		resp, err := client.GetBranchProtectionRulesWithResponse(cmd.Context(), u.Repository)
		DieOnResponseError(resp, err)
		patterns := make([][]interface{}, len(*resp.JSON200))
		for i, rule := range *resp.JSON200 {
			patterns[i] = []interface{}{rule.Pattern}
		}
		PrintTable(patterns, []interface{}{"Branch Pattern Name"}, &api.Pagination{
			HasMore: false,
			Results: len(patterns),
		}, len(patterns))
	},
}

var branchProtectAddCmd = &cobra.Command{
	Use:     "add <repo uri> <pattern>",
	Short:   "Add a branch protection rule",
	Long:    "Add a branch protection rule for a given branch pattern name",
	Example: "lakectl add lakefs://<repository> stable/*",
	Args:    cobra.ExactArgs(branchProtectAddCmdArgs),
	Run: func(cmd *cobra.Command, args []string) {
		client := getClient()
		u := MustParseRepoURI("repo", args[0])
		resp, err := client.CreateBranchProtectionRuleWithResponse(cmd.Context(), u.Repository, api.CreateBranchProtectionRuleJSONRequestBody{
			Pattern: args[1],
		})
		DieOnResponseError(resp, err)
	},
}

var branchProtectDeleteCmd = &cobra.Command{
	Use:     "delete <repo uri> <pattern>",
	Short:   "Delete a branch protection rule",
	Long:    "Delete a branch protection rule for a given branch pattern name",
	Example: "lakectl delete lakefs://<repository> stable/*",
	Args:    cobra.ExactArgs(branchProtectAddCmdArgs),
	Run: func(cmd *cobra.Command, args []string) {
		client := getClient()
		u := MustParseRepoURI("repo", args[0])
		resp, err := client.DeleteBranchProtectionRuleWithResponse(cmd.Context(), u.Repository, api.DeleteBranchProtectionRuleJSONRequestBody{
			Pattern: args[1],
		})
		DieOnResponseError(resp, err)
	},
}

//nolint:gochecknoinits
func init() {
	rootCmd.AddCommand(branchProtectCmd)
	branchProtectCmd.AddCommand(branchProtectAddCmd)
	branchProtectCmd.AddCommand(branchProtectListCmd)
	branchProtectCmd.AddCommand(branchProtectDeleteCmd)
}
