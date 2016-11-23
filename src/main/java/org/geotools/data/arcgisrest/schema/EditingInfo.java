
package org.geotools.data.arcgisrest.schema;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

public class EditingInfo {

    /**
     * 
     * (Required)
     * 
     */
    @SerializedName("lastEditDate")
    @Expose
    private Integer lastEditDate;

    /**
     * 
     * (Required)
     * 
     * @return
     *     The lastEditDate
     */
    public Integer getLastEditDate() {
        return lastEditDate;
    }

    /**
     * 
     * (Required)
     * 
     * @param lastEditDate
     *     The lastEditDate
     */
    public void setLastEditDate(Integer lastEditDate) {
        this.lastEditDate = lastEditDate;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(lastEditDate).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof EditingInfo) == false) {
            return false;
        }
        EditingInfo rhs = ((EditingInfo) other);
        return new EqualsBuilder().append(lastEditDate, rhs.lastEditDate).isEquals();
    }

}