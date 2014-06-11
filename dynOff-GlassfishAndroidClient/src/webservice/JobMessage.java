/**
 * JobMessage.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package webservice;

public class JobMessage  implements java.io.Serializable {
    private java.lang.String actorid;

    private byte[] message;

    private int waittime;

    public JobMessage() {
    }

    public JobMessage(
           java.lang.String actorid,
           byte[] message,
           int waittime) {
           this.actorid = actorid;
           this.message = message;
           this.waittime = waittime;
    }


    /**
     * Gets the actorid value for this JobMessage.
     * 
     * @return actorid
     */
    public java.lang.String getActorid() {
        return actorid;
    }


    /**
     * Sets the actorid value for this JobMessage.
     * 
     * @param actorid
     */
    public void setActorid(java.lang.String actorid) {
        this.actorid = actorid;
    }


    /**
     * Gets the message value for this JobMessage.
     * 
     * @return message
     */
    public byte[] getMessage() {
        return message;
    }


    /**
     * Sets the message value for this JobMessage.
     * 
     * @param message
     */
    public void setMessage(byte[] message) {
        this.message = message;
    }


    /**
     * Gets the waittime value for this JobMessage.
     * 
     * @return waittime
     */
    public int getWaittime() {
        return waittime;
    }


    /**
     * Sets the waittime value for this JobMessage.
     * 
     * @param waittime
     */
    public void setWaittime(int waittime) {
        this.waittime = waittime;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof JobMessage)) return false;
        JobMessage other = (JobMessage) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.actorid==null && other.getActorid()==null) || 
             (this.actorid!=null &&
              this.actorid.equals(other.getActorid()))) &&
            ((this.message==null && other.getMessage()==null) || 
             (this.message!=null &&
              java.util.Arrays.equals(this.message, other.getMessage()))) &&
            this.waittime == other.getWaittime();
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getActorid() != null) {
            _hashCode += getActorid().hashCode();
        }
        if (getMessage() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getMessage());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getMessage(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        _hashCode += getWaittime();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(JobMessage.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://webservice/", "jobMessage"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("actorid");
        elemField.setXmlName(new javax.xml.namespace.QName("", "actorid"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("message");
        elemField.setXmlName(new javax.xml.namespace.QName("", "message"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "base64Binary"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("waittime");
        elemField.setXmlName(new javax.xml.namespace.QName("", "waittime"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
