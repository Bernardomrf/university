import java.io.*;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.io.*;
import java.security.*;
import javax.crypto.*;
import java.security.spec.*;

public class DecipherRSA{
  public static void main(String args[]){

    if(args.length !=3 ){
      System.out.println("Wrong arguments number");
      System.exit(-1);
    }



    try{

      PrivateKey priv = get(args[0]);
      File text = new File(args[1]);
      File out = new File(args[2]);

      FileInputStream ftext = new FileInputStream(text);
      FileOutputStream fout = new FileOutputStream(out);

      byte[] cipherText;


      Cipher cipher = Cipher.getInstance("RSA");
      cipher.init(Cipher.DECRYPT_MODE, priv);

      int blockSize = cipher.getBlockSize();

      byte[] dataBlock = new byte[blockSize];

      ftext.read(dataBlock);

      cipherText = cipher.doFinal(dataBlock);

      fout.write(cipherText);

      fout.close();


    }catch(Exception e){
      System.out.println("ERRO!");
      System.exit(-1);
    }
  }

  public static PrivateKey get(String filename)
  throws Exception {

    File f = new File(filename);
    FileInputStream fis = new FileInputStream(f);
    DataInputStream dis = new DataInputStream(fis);
    byte[] keyBytes = new byte[(int)f.length()];
    dis.readFully(keyBytes);
    dis.close();

    PKCS8EncodedKeySpec spec =
      new PKCS8EncodedKeySpec(keyBytes);
    KeyFactory kf = KeyFactory.getInstance("RSA");
    return kf.generatePrivate(spec);
  }


}
