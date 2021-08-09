

package Controller;

import Model.User;
import java.util.List;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import java.io.File;
import org.apache.commons.fileupload.FileUploadException;


public class FileController {
   public FileController(){
    this.setCoverPath("C:\\Users\\Mumin\\Documents\\NetBeansProjects\\Twitter\\web\\imgs\\cover");
    this.setProfilePath("C:\\Users\\Mumin\\Documents\\NetBeansProjects\\Twitter\\web\\imgs\\profile");
           
    }
  
private String coverPath = "";
    private String profilePath = "";
    public String getCoverPath() {
        return coverPath;
    }

    public final void setCoverPath(String coverPath) {
        this.coverPath = coverPath;
    }

    public String getProfilePath() {
        return profilePath;
    }

    private void setProfilePath(String profilePath) {
        this.profilePath = profilePath;
    }
    
    private void uploadCover(FileItem item,User usr)
    {
    try {
        String fileName = new File(AuthoriseUser.getUser(usr)+"_"+"cover.jpg").getName();
        
        String filePath = this.getCoverPath() + File.separator + fileName;
        
        File storeFile = new File(filePath);
        
        // saves the file on disk
        item.write(storeFile);
        usr.setMessage("Cover has been uploaded succesfully!!..");
    } catch (Exception ex) {
          usr.setMessage(ex.getMessage());
          }
                        
    }
    
    private boolean isValid(FileItem item)
    {
        return item.getName().endsWith("jpeg")||item.getName().endsWith("png")||item.getName().endsWith("jpg");
    }
    
    private void uploadProfile(FileItem item,User usr)
    {
    try {
        String fileName = new File(AuthoriseUser.getUser(usr)+"_"+"profile.jpg").getName();
        
        String filePath = this.getProfilePath()+ File.separator + fileName;
        
        File storeFile = new File(filePath);
        
        // saves the file on disk
        item.write(storeFile);
        usr.setMessage(
                "Profile" + "Upload has been done successfully!");
    } catch (Exception ex) {
        usr.setMessage(ex.getMessage());
         }
    }
    
    public void uploadPics(User usr)
    {
      if(usr.getRequest().getMethod().equals("POST"))
      {
          if (usr.getRequest().getContentType() != null && usr.getRequest().getContentType().toLowerCase().contains("multipart/form-data") ) {
// Multipart logic here
           DiskFileItemFactory factory = new DiskFileItemFactory();
           ServletFileUpload upload = new ServletFileUpload(factory);
           
            try {
            
            @SuppressWarnings("unchecked")
            List<FileItem> formItems = upload.parseRequest(usr.getRequest());
 
            if (formItems != null && formItems.size() > 0) {
                formItems.stream().filter((item) -> (!item.isFormField())).forEach((FileItem item) -> {
                    switch (item.getFieldName()) {
                        case "coverPic":
                            if(this.isValid(item)){
                            FileController.this.uploadCover(item, usr);
                            }
                            else{usr.setMessage("INVALID FILE PLEASE USE AN IMAGE >>' ");}
                            break;
                        case "profilePic":
                            if(this.isValid(item))
                            {
                            FileController.this.uploadProfile(item, usr);}
                            else{usr.setMessage("INVALID FILE PLEASE USE AN IMAGE >>' ");}
                            break;
                    }
                });
            }
        } catch (FileUploadException ex) {
            usr.setMessage("There was an error: " + ex.getMessage());
        }
 
            
      } 
      }
    }
    
    public static String getCoverPic(User usr)
    {
        String realPath = "C:\\Users\\Mumin\\Documents\\NetBeansProjects\\Twitter\\web\\imgs";  
       File file = new File(realPath+"\\cover\\"+usr.getUsername()+"_cover.jpg"); 
       
        
      if(file.exists())
      {
    
          return "<img class='img-fluidc cover_pic' style=' object-fit: cover;'  src='imgs/cover/"+usr.getUsername()+"_cover.jpg'>";
      } 
      return " <img src=\"imgs/cover_pic.png\" class=\"img-fluidc cover_pic\" alt=\"...\">";
    }
     public static String getProfilePic(User usr)
    {
         String realPath = "C:\\Users\\Mumin\\Documents\\NetBeansProjects\\Twitter\\web\\imgs";  
       File file = new File(realPath+"\\profile\\"+usr.getUsername()+"_profile.jpg"); 
        
      if(file.exists())
      {
        
          return "<img class='img-fluid profile_pic' style='  max-width: 100%;  height: auto; object-fit: cover;'  src='imgs/profile/"+usr.getUsername()+"_profile.jpg'>";
      } 
      return "<img class='img-fluid profile_pic' src='imgs/profile_pic.jpg' >";
    }
      public static String getProfilePic(User usr,String name)
    {
         String realPath = "C:\\Users\\Mumin\\Documents\\NetBeansProjects\\Twitter\\web\\imgs";  
       File file = new File(realPath+"\\profile\\"+name+"_profile.jpg"); 
        
      if(file.exists())
      {
        
          return "<img class='img-fluid profile_pic' style='  max-width: 100%;  height: auto; object-fit: cover;'  src='imgs/profile/"+name+"_profile.jpg'>";
      } 
      return "<img class='img-fluid profile_pic' src='imgs/profile_pic.jpg' >";
    }
     public static String getProfileUploadForm()
     {
       return " <form id=\"profileForm\" method=\"POST\"enctype=\"multipart/form-data\">\n" +
                " <input type=\"file\"  id=\"profileinput\" name=\"profilePic\" onchange=\"submit()\" />\n" +
                " </form>";
     }
    
      public static String getCoverUploadForm()
     {
       return " <form id=\"coverForm\" enctype=\"multipart/form-data\" method=\"POST\">\n" +
                "<input type=\"file\" id=\"coverinput\" name=\"coverPic\"  onchange=\"submit()\"/>\n" +
                "</form> ";
     }
      public static String clearUrl()
      {
          return "jQuery(document).ready(function($) {\n" +
"\n" +
"var url = window.location.href;\n" +
"      alert(url.split('?')[0]);\n" +
"});";
      
      }
            
}
