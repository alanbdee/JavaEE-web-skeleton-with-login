package presentation;

import boundary.DocumentsFacade;
import entities.Agents;
import entities.Companies;
import entities.Documents;
import java.io.Serializable;
import java.util.List;
import java.util.Set;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.RateEvent;
import org.primefaces.model.UploadedFile;

@ManagedBean(name = "documentController")
@SessionScoped
public class DocumentController implements Serializable {
    @ManagedProperty(value="#{loginController}")
    private LoginController loginController;

    public LoginController getLoginController() {
        return loginController;
    }

    public void setLoginController(LoginController loginController) {
        this.loginController = loginController;
    }
    @EJB
    private boundary.DocumentsFacade docFacade;
    private long id;
    private String type = "AGENT";
    private String title;
    private String desc;
    private int rank;

    public String getType() {
        //return getDocFacade().getTempDocument().getTypeString();
        return type;
    }

    public void setType(String type) {
        //getDocFacade().getTempDocument().setUserType(type);
        LoginController lc = getLoginController();
        Agents agent = lc.getAgent();
        Companies company = lc.getCompany();
        if(type.equals("AGENT")){
            //getDocFacade().getTempDocument().setUserID(getLoginController().getAgent().getId());
        } else {
            //getDocFacade().getTempDocument().setUserID(getLoginController().getCompany().getId());
        }
        this.type = this.getType();
    }

    public String getDesc() {
        return getDocFacade().getTempDocument().getDescription();
    }

    public void setDesc(String desc) {
        getDocFacade().getTempDocument().setDescription(desc);
        this.desc = this.getDesc();
    }

    public int getRank() {
        return getDocFacade().getTempDocument().getRank();
    }

    public void setRank(int rank) {
        getDocFacade().getTempDocument().setRank(rank);
        this.rank = this.getRank();
    }

    public String getTitle() {
        return getDocFacade().getTempDocument().getTitle();
    }

    public void setTitle(String title) {
        getDocFacade().getTempDocument().setTitle(title);
        this.title = this.getTitle();
    }

    public DocumentsFacade getDocFacade() {
        return docFacade;
    }

    public void setDocFacade(DocumentsFacade docFacade) {
        this.docFacade = docFacade;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void openDocument() {
        System.out.println("Open Document");
    }
//    public void handleFileUpload(FileUploadEvent event) {
//        System.out.println("File Uploaded");
//        FacesMessage msg = new FacesMessage("Succesful", event.getFile().getFileName() + " is uploaded.");
//        FacesContext.getCurrentInstance().addMessage(null, msg);
//    }
    //Primitives
    private static final int BUFFER_SIZE = 6124;
    private String folderToUpload;
    private List<byte[]> files;

    public void handleFileUpload(FileUploadEvent event) {
        System.out.println("File Uploaded");
        ExternalContext extContext = FacesContext.getCurrentInstance().getExternalContext();
        UploadedFile uploadedFile = event.getFile();
        byte[] contents = uploadedFile.getContents();
        Documents tempDocument = getDocFacade().getTempDocument();
        tempDocument.setStatus("TMP");
        tempDocument.addPage(contents);
        try {
            getDocFacade().create(tempDocument);
        } catch (javax.persistence.RollbackException re) {
            Throwable cause = re.getCause();
        } catch (javax.ejb.EJBException ejb) {
            Exception causedByException = ejb.getCausedByException();
            try {
                throw causedByException;
            } catch (javax.persistence.RollbackException re) {
                Throwable cause = re.getCause();
            } catch (ConstraintViolationException e) {
                Set<ConstraintViolation<?>> constraintViolations = e.getConstraintViolations();
                String localizedMessage = e.getLocalizedMessage();
                String message2 = e.getMessage();
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Rate Event", e.getMessage());
                FacesContext.getCurrentInstance().addMessage(null, message);
            } catch (Exception e) {
            }
        }


        //event.file.fileItem.tempFile.path
        //        File result = new File(extContext.getRealPath("//WEB-INF//files//" + event.getFile().getFileName()));
        //
        //        try {
        //            FileOutputStream fileOutputStream = new FileOutputStream(result);
        //
        //            byte[] buffer = new byte[BUFFER_SIZE];
        //
        //            int bulk;
        //            InputStream inputStream = event.getFile().getInputstream();
        //            while (true) {
        //                bulk = inputStream.read(buffer);
        //                if (bulk < 0) {
        //                    break;
        //                }
        //                fileOutputStream.write(buffer, 0, bulk);
        //                fileOutputStream.flush();
        //            }
        //
        //            fileOutputStream.close();
        //            inputStream.close();
        //
        //            FacesMessage msg = new FacesMessage("Succesful", event.getFile().getFileName()
        //                    + " is uploaded.");
        //            FacesContext.getCurrentInstance().addMessage(null, msg);
        //        } catch (IOException e) {
        //            e.printStackTrace();
        //            FacesMessage error = new FacesMessage(FacesMessage.SEVERITY_ERROR,
        //                    "The files were not uploaded!", "");
        //            FacesContext.getCurrentInstance().addMessage(null, error);
        //        }
    }

    public void handleRate(RateEvent rateEvent) {
        this.setRank(((Integer) rateEvent.getRating()).intValue());
        //FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Rate Event", "You rated:" + ((Double) rateEvent.getRating()).intValue());
        //FacesContext.getCurrentInstance().addMessage(null, message);
    }
}
