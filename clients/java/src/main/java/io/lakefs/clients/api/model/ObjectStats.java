/*
 * lakeFS API
 * lakeFS HTTP API
 *
 * The version of the OpenAPI document: 0.1.0
 * 
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */


package io.lakefs.clients.api.model;

import java.util.Objects;
import java.util.Arrays;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ObjectStats
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen")
public class ObjectStats {
  public static final String SERIALIZED_NAME_PATH = "path";
  @SerializedName(SERIALIZED_NAME_PATH)
  private String path;

  /**
   * Gets or Sets pathType
   */
  @JsonAdapter(PathTypeEnum.Adapter.class)
  public enum PathTypeEnum {
    COMMON_PREFIX("common_prefix"),
    
    OBJECT("object");

    private String value;

    PathTypeEnum(String value) {
      this.value = value;
    }

    public String getValue() {
      return value;
    }

    @Override
    public String toString() {
      return String.valueOf(value);
    }

    public static PathTypeEnum fromValue(String value) {
      for (PathTypeEnum b : PathTypeEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }

    public static class Adapter extends TypeAdapter<PathTypeEnum> {
      @Override
      public void write(final JsonWriter jsonWriter, final PathTypeEnum enumeration) throws IOException {
        jsonWriter.value(enumeration.getValue());
      }

      @Override
      public PathTypeEnum read(final JsonReader jsonReader) throws IOException {
        String value =  jsonReader.nextString();
        return PathTypeEnum.fromValue(value);
      }
    }
  }

  public static final String SERIALIZED_NAME_PATH_TYPE = "path_type";
  @SerializedName(SERIALIZED_NAME_PATH_TYPE)
  private PathTypeEnum pathType;

  public static final String SERIALIZED_NAME_PHYSICAL_ADDRESS = "physical_address";
  @SerializedName(SERIALIZED_NAME_PHYSICAL_ADDRESS)
  private String physicalAddress;

  public static final String SERIALIZED_NAME_CHECKSUM = "checksum";
  @SerializedName(SERIALIZED_NAME_CHECKSUM)
  private String checksum;

  public static final String SERIALIZED_NAME_SIZE_BYTES = "size_bytes";
  @SerializedName(SERIALIZED_NAME_SIZE_BYTES)
  private Long sizeBytes;

  public static final String SERIALIZED_NAME_MTIME = "mtime";
  @SerializedName(SERIALIZED_NAME_MTIME)
  private Long mtime;

  public static final String SERIALIZED_NAME_METADATA = "metadata";
  @SerializedName(SERIALIZED_NAME_METADATA)
  private Map<String, String> metadata = null;


  public ObjectStats path(String path) {
    
    this.path = path;
    return this;
  }

   /**
   * Get path
   * @return path
  **/
  @ApiModelProperty(required = true, value = "")

  public String getPath() {
    return path;
  }


  public void setPath(String path) {
    this.path = path;
  }


  public ObjectStats pathType(PathTypeEnum pathType) {
    
    this.pathType = pathType;
    return this;
  }

   /**
   * Get pathType
   * @return pathType
  **/
  @ApiModelProperty(required = true, value = "")

  public PathTypeEnum getPathType() {
    return pathType;
  }


  public void setPathType(PathTypeEnum pathType) {
    this.pathType = pathType;
  }


  public ObjectStats physicalAddress(String physicalAddress) {
    
    this.physicalAddress = physicalAddress;
    return this;
  }

   /**
   * Get physicalAddress
   * @return physicalAddress
  **/
  @ApiModelProperty(required = true, value = "")

  public String getPhysicalAddress() {
    return physicalAddress;
  }


  public void setPhysicalAddress(String physicalAddress) {
    this.physicalAddress = physicalAddress;
  }


  public ObjectStats checksum(String checksum) {
    
    this.checksum = checksum;
    return this;
  }

   /**
   * Get checksum
   * @return checksum
  **/
  @ApiModelProperty(required = true, value = "")

  public String getChecksum() {
    return checksum;
  }


  public void setChecksum(String checksum) {
    this.checksum = checksum;
  }


  public ObjectStats sizeBytes(Long sizeBytes) {
    
    this.sizeBytes = sizeBytes;
    return this;
  }

   /**
   * Get sizeBytes
   * @return sizeBytes
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public Long getSizeBytes() {
    return sizeBytes;
  }


  public void setSizeBytes(Long sizeBytes) {
    this.sizeBytes = sizeBytes;
  }


  public ObjectStats mtime(Long mtime) {
    
    this.mtime = mtime;
    return this;
  }

   /**
   * Unix Epoch in seconds
   * @return mtime
  **/
  @ApiModelProperty(required = true, value = "Unix Epoch in seconds")

  public Long getMtime() {
    return mtime;
  }


  public void setMtime(Long mtime) {
    this.mtime = mtime;
  }


  public ObjectStats metadata(Map<String, String> metadata) {
    
    this.metadata = metadata;
    return this;
  }

  public ObjectStats putMetadataItem(String key, String metadataItem) {
    if (this.metadata == null) {
      this.metadata = new HashMap<String, String>();
    }
    this.metadata.put(key, metadataItem);
    return this;
  }

   /**
   * Get metadata
   * @return metadata
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public Map<String, String> getMetadata() {
    return metadata;
  }


  public void setMetadata(Map<String, String> metadata) {
    this.metadata = metadata;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ObjectStats objectStats = (ObjectStats) o;
    return Objects.equals(this.path, objectStats.path) &&
        Objects.equals(this.pathType, objectStats.pathType) &&
        Objects.equals(this.physicalAddress, objectStats.physicalAddress) &&
        Objects.equals(this.checksum, objectStats.checksum) &&
        Objects.equals(this.sizeBytes, objectStats.sizeBytes) &&
        Objects.equals(this.mtime, objectStats.mtime) &&
        Objects.equals(this.metadata, objectStats.metadata);
  }

  @Override
  public int hashCode() {
    return Objects.hash(path, pathType, physicalAddress, checksum, sizeBytes, mtime, metadata);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ObjectStats {\n");
    sb.append("    path: ").append(toIndentedString(path)).append("\n");
    sb.append("    pathType: ").append(toIndentedString(pathType)).append("\n");
    sb.append("    physicalAddress: ").append(toIndentedString(physicalAddress)).append("\n");
    sb.append("    checksum: ").append(toIndentedString(checksum)).append("\n");
    sb.append("    sizeBytes: ").append(toIndentedString(sizeBytes)).append("\n");
    sb.append("    mtime: ").append(toIndentedString(mtime)).append("\n");
    sb.append("    metadata: ").append(toIndentedString(metadata)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }

}

