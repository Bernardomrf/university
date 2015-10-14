import java.io.*;
import java.security.KeyPair;
import java.security.KeyPairGenerator;

public class AsymKeys{
  public static void main(String args[]){

    if(args.length !=3 ){
      System.out.println("Wrong arguments number");
      System.exit(-1);
    }
    int keySyze = Integer.parseInt(args[0]);
    File pub = new File(args[1]);
    File priv = new File(args[2]);

    try{

      FileOutputStream publicKey = new FileOutputStream(pub);
      FileOutputStream privateKey = new FileOutputStream(priv);

      KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
      kpg.initialize(keySyze);
      KeyPair keyPair = kpg.generateKeyPair();

      publicKey.write(keyPair.getPublic().getEncoded());
      privateKey.write(keyPair.getPrivate().getEncoded());

      publicKey.close();
      privateKey.close();

    }catch(Exception e){
      System.out.println("ERRO!");
      System.exit(-1);
    }
  }
}
