/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import org.apache.commons.codec.digest.DigestUtils;

/**
 *
 * @author Alan
 */
@Entity
public class DocumentPages implements Serializable {
    public static final String HASH_METHOD = "MD5";
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    //@ManyToOne (cascade={CascadeType.REFRESH})
    @ManyToOne
    @JoinColumn(name = "DOCUMENT_ID")
    private Documents document;
    @NotNull
    private int pageNum;
    
    @NotNull
    @Column(length=36)
    private String docHash;
    @Lob
    @Column(length=32000)
    private String OCR;
    
    @Basic(fetch= FetchType.LAZY)
    @Lob 
    private byte[] image;
    @Column(length=10)
    private String status;

    public DocumentPages() {
    }

    public DocumentPages(Documents document, byte[] image) {
        this.setDocument(document);
        this.setImage(image);
        this.setPageNum(0);
    }
    public DocumentPages(Documents document, byte[] image, int pageNum) {
        this.setDocument(document);
        this.setImage(image);
        this.setPageNum(pageNum);
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public String getOCR() {
        return OCR;
    }

    public void setOCR(String OCR) {
        this.OCR = OCR;
    }

    public String getDocHash() {
        return docHash;
    }

    public void setDocHash(String docHash) {
        this.docHash = docHash;
    }

    public Documents getDocument() {
        return document;
    }

    public final void setDocument(Documents document) {
        this.document = document;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        //identify hash here
        this.docHash = DigestUtils.md5Hex(image);
        this.image = image;
    }
    
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DocumentPages)) {
            return false;
        }
        DocumentPages other = (DocumentPages) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Documents[ id=" + id + " ]";
    }

    
}
