package com.example.bsep.model;

import java.util.List;

public class CertificateAuthority {

    private CertificateAuthority parent;

    private CertificateData data;

    private List<Certificate> children;

    private CertificateType type;

    

    CertificateAuthority(){}

    public CertificateAuthority getParent() {
        return this.parent;
    }

    public void setParent(CertificateAuthority parent) {
        this.parent = parent;
    }

    public CertificateData getData() {
        return this.data;
    }

    public void setData(CertificateData data) {
        this.data = data;
    }

    public List<Certificate> getChildren() {
        return this.children;
    }

    public void setChildren(List<Certificate> children) {
        this.children = children;
    }

    public CertificateType getType() {
        return this.type;
    }

    public void setType(CertificateType type) {
        this.type = type;
    }
}