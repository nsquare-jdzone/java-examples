package com.javadeveloperzone;

import java.util.List;

public class Document {
    int documentId,parentDocId;
    String docType,docAuthor,docTitle;
    boolean isParent;
    List<String> docLanguage;

    public int getDocumentId() {
        return documentId;
    }

    @Override
    public String toString() {
        return "Document{" +
                "documentId=" + documentId +
                ", parentDocId=" + parentDocId +
                ", docType='" + docType + '\'' +
                ", docAuthor='" + docAuthor + '\'' +
                ", docTitle='" + docTitle + '\'' +
                ", isParent=" + isParent +
                ", docLanguage=" + docLanguage +
                '}';
    }

    public void setDocumentId(int documentId) {
        this.documentId = documentId;
    }

    public int getParentDocId() {
        return parentDocId;
    }

    public void setParentDocId(int parentDocId) {
        this.parentDocId = parentDocId;
    }

    public String getDocType() {
        return docType;
    }

    public void setDocType(String docType) {
        this.docType = docType;
    }

    public String getDocAuthor() {
        return docAuthor;
    }

    public void setDocAuthor(String docAuthor) {
        this.docAuthor = docAuthor;
    }

    public String getDocTitle() {
        return docTitle;
    }

    public void setDocTitle(String docTitle) {
        this.docTitle = docTitle;
    }

    public boolean isParent() {
        return isParent;
    }

    public void setParent(boolean parent) {
        isParent = parent;
    }

    public List<String> getDocLanguage() {
        return docLanguage;
    }

    public void setDocLanguage(List<String> docLanguage) {
        this.docLanguage = docLanguage;
    }
}
