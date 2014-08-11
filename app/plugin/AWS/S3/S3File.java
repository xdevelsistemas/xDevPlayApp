package plugin.AWS.S3;

/**
 * Created by claytonsantosdasilva on 11/08/14.
 */
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import models.AbstractModel;
import play.Logger;

import javax.persistence.Entity;
import javax.persistence.Transient;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

@Entity
public class S3File extends AbstractModel {


    public S3File(String ybucket ){
        this.bucket = ybucket;
    }


    private String bucket;


    @Transient
    public File file;

    public URL getUrl() throws MalformedURLException {
        return new URL("https://s3.amazonaws.com/" + bucket + "/" + getActualFileName());
    }

    private String getActualFileName() {
        return uuid +  ".pdf";
    }


    public void save(String name) {
        if (S3Plugin.amazonS3 == null) {
            Logger.error("Could not save because amazonS3 was null");
            throw new RuntimeException("Could not save");
        }
        else {
            this.bucket = S3Plugin.s3Bucket;
            file = new File(name);

            uuid = name.replace(".pdf", "").replace("public/Boletos/","");

            PutObjectRequest putObjectRequest = new PutObjectRequest(bucket, getActualFileName(), file);
            putObjectRequest.withCannedAcl(CannedAccessControlList.PublicRead); // public for all
            S3Plugin.amazonS3.putObject(putObjectRequest); // upload file
        }
    }

//    @Override
//    public void delete() {
//        if (S3Plugin.amazonS3 == null) {
//            Logger.error("Could not delete because amazonS3 was null");
//            throw new RuntimeException("Could not delete");
//        }
//        else {
//            S3Plugin.amazonS3.deleteObject(bucket, getActualFileName());
//            super.delete();
//        }
//    }

}