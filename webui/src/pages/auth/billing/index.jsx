import React, {useEffect, useState} from "react";


import {Route, Switch} from "react-router-dom";
import {AuthLayout} from "../../../lib/components/auth/layout";


const BillingView = () => {
    return (
        <>
            <h1>Nothing to find here</h1>
        </>
    );
}


const BillingIndexPage = () => {
    return (
        <AuthLayout activeTab={"billing"}>
            <BillingView/>
        </AuthLayout>
    )
}

export default BillingIndexPage;