package sample;

import java.io.*;
import java.net.Authenticator;
import java.util.Iterator;
import java.util.Properties;

import com.microsoft.windowsazure.storage.*;
import com.microsoft.windowsazure.storage.blob.*;

public class BlobSample {
    public static final String storageConnectionString =
        "DefaultEndpointsProtocol=https;"
        + "AccountName=omnidocsintegration;"
        + "AccountKey=nVqum+MBG/FLI4Z/mjPkhdKurKUTxW/bFfa2kTN6L7tDjT6xEL2tFwGCV5OUJaDsNXs4RgP1lmlvWAm62SmQdA==";

    public static void main(String[] args) {
        try {
        	
        	/* Properties properties = System.getProperties();
             properties.put("https.proxyHost", "192.168.55.218");
             properties.put("https.proxyPort", "8080");
             properties.put("https.proxyUser", "malkeet.singh");
             properties.put("https.proxyPassword","newgen123~");
             //System.out.println("Password and Username NOT added to system properties 29may ");
             System.out.println("Password and Username added to system properties 29may ");
             System.setProperties(properties);
             System.setProperty("jsse.enableSNIExtension","false");
           
             String path2=System.getProperty("https.proxyHost");
             System.out.println("host is :"+path2);
        	
        	Authenticator.setDefault(new ProxyAuthenticator("malkeet.singh", "newgen123~"));
        	System.setProperty("http.proxyHost", "192.168.55.218");
        	System.setProperty("http.proxyPort", "8080");*/
             

        	
            CloudStorageAccount account = CloudStorageAccount.parse(storageConnectionString);
            CloudBlobClient serviceClient = account.createCloudBlobClient();
            System.out.println("connect Success"+serviceClient.getStorageUri());
            System.out.println("connect Success"+serviceClient.listContainers());
            /*Iterable<CloudBlobContainer> flavoursIter = serviceClient.listContainers();
            System.out.println(flavoursIter);
            Iterator<CloudBlobContainer> ite=flavoursIter.iterator();
            System.out.println(ite);
            while (ite.hasNext()){
              System.out.println(ite.toString());
            }*/
            
            for(CloudBlobContainer item:serviceClient.listContainers() ){
            	System.out.println(item.getName());
            }
            
            
            // Container name must be lower case.
            CloudBlobContainer container = serviceClient.getContainerReference("od83");
            container.createIfNotExists();
            System.out.println("container Success");
            
            for (ListBlobItem blobItem : container.listBlobs()) {
                System.out.println(blobItem.getUri());
            }
            
           //  Upload an image file.
         CloudBlockBlob blob = container.getBlockBlobReference("sdas.txt");
           File sourceFile = new File("D:\\sdas.txt");
           blob.upload(new FileInputStream(sourceFile), sourceFile.length());
            
         //   Download the image file.
           File destinationFile = new File(sourceFile.getParentFile(), "sdas.txt");
            blob.downloadToFile(destinationFile.getAbsolutePath());
           
            
            
           // CloudBlockBlob blob = container.getBlockBlobReference("ITWORKORDR_omnideskdb_manoj-jain.GIF_6842.GIF");
            //blob.deleteIfExists();
            
            for (ListBlobItem blobItem : container.listBlobs()) {
                System.out.println(blobItem.getUri());
            }
        }
       /* catch (FileNotFoundException fileNotFoundException) {
            System.out.print("FileNotFoundException encountered: ");
            fileNotFoundException.printStackTrace();
            System.exit(-1);
        }*/
        catch (StorageException storageException) {
            System.out.print("StorageException encountered: ");
            storageException.printStackTrace();
            System.exit(-1);
        }
        catch (Exception e) {
            System.out.print("Exception encountered: ");
            e.printStackTrace();
            System.exit(-1);
        }
    }
}