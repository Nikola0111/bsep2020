package com.example.bsep.model;

import java.util.List;

public class CertificateAuthority {

    private Certificate parent;

    private CertificateData data;

    private List<Certificate> children;



    
    CertificateAuthority(){}

    

    public Certificate getParent() {
        return this.parent;
    }

    public void setParent(Certificate parent) {
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
}