import java.io.*;
import javax.crypto.*;
import javax.crypto.KeyGenerator;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;


public class DCifraDES{

  public static void main(String args[]){
    if(args.length != 4){
      System.exit(-1);
    }

    String algo = args[0];
    File fout = new File(args[2]);
    File fin = new File(args[1]);
    SecretKey secretKey;

    try{

      byte[] keyData = args[3].getBytes();
      SecretKeySpec sks = new SecretKeySpec(keyData,algo);
      SecretKeyFactory kf = SecretKeyFactory.getInstance(algo);
      secretKey = kf.generateSecret(sks);

      byte[] cipherText;
      FileInputStream fisin = new FileInputStream(fin);

      Cipher c = Cipher.getInstance("DES/ECB/NoPadding");
      c.init(Cipher.DECRYPT_MODE, secretKey);

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
