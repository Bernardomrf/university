import java.io.*;
import javax.crypto.*;
import javax.crypto.KeyGenerator;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;


public class CifraDES{

  public static void main(String args[]){
    if(args.length !=5 ){
      System.out.println("Usage java CifraDES (AES or DES) (0 to encrypt 1 to decrypt) in.txt out.txt password /n or /njava CifraDES (AES or DES) in.txt out.txt password");
      System.exit(-1);
    }

    String algo = args[0];
    File fout = new File(args[3]);
    File fin = new File(args[2]);
    String password = args[4];
    SecretKey secretKey;

    try{

      byte[] keyData = password.getBytes();
      SecretKeySpec sks = new SecretKeySpec(keyData,algo);
      SecretKeyFactory kf = SecretKeyFactory.getInstance(algo);
      secretKey = kf.generateSecret(sks);

      byte[] cipherText;
      FileInputStream fisin = new FileInputStream(fin);

      Cipher c = Cipher.getInstance("AES/ECB/NoPadding");
      if(Integer.parseInt(args[1]) ==0){
        c.init(Cipher.ENCRYPT_MODE, secretKey);
      }
      else if(Integer.parseInt(args[1]) ==1){
        c.init(Cipher.DECRYPT_MODE, secretKey);
      }


      long bytesRead = 0;
      long fileSize = fin.length();
      int blockSize = c.getBlockSize();

      FileOutputStream fos = new FileOutputStream(fout);

      while (bytesRead < fileSize){
        byte[] dataBlock = new byte[blockSize];
        bytesRead += fisin.read(dataBlock);
        cipherText = c.update(dataBlock);
        //Do something with cipherText

        fos.write(cipherText);

      }
      fos.close();

      cipherText = c.doFinal();

    }catch(Exception e){
      System.out.println("ERRO");
    }
  }

}
