/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

/**
 *
 * @author Nicolas
 */
public abstract class Usuario {

    private String nome;
    private int codigoUnitario;
    private int codigoFilial;
    private int codigoPerfil;
    private String login;
    private char[] hashSenha;
    private boolean status;
    
    public Usuario()
    {
        
    }

    //Inicia o usuario com status ativo
    public Usuario(String nome, int codUnitario, int codFilial, int codPerfil, String login,
            String senha) {
        this.nome = nome;
        this.codigoUnitario = codUnitario;
        this.codigoFilial = codFilial;
        this.login = login;
        this.codigoPerfil = codPerfil;
        this.status = true;
        
        try {
            this.hashSenha = gerarHashSenhaPBKDF2(senha);
        } catch(NoSuchAlgorithmException | InvalidKeySpecException ex) {
            Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getCodigoUnitario() {
        return codigoUnitario;
    }

    public void setCodigoUnitario(int codigoUnitario) {
        this.codigoUnitario = codigoUnitario;
    }

    public int getCodigoFilial() {
        return codigoFilial;
    }

    public void setCodigoFilial(int codigoFilial) {
        this.codigoFilial = codigoFilial;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public char[] getHashSenha() {
        return hashSenha;
    }

    public int getCodigoPerfil() {
        return this.codigoPerfil;
    }

    public void setCodigoPerfil(char tipo) {
        this.codigoPerfil = tipo;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    private char[] gerarHashSenhaMD5(String senha) {
        try {
          // SALT (EM SITUACOES REAIS, DEVEM SER DIFERENTES PARA CADA USUARIO)
          String salt = "AT" + this.nome + "AT";

          MessageDigest md = MessageDigest.getInstance("MD5");
          md.reset();
          byte[] digested = md.digest((salt + senha).getBytes());
          StringBuilder sb = new StringBuilder();
          for (int i = 0; i < digested.length; i++) {
            sb.append(Integer.toHexString(0xff & digested[i]));
          }
          return sb.toString().toCharArray();
        } catch (NoSuchAlgorithmException ex) {
          //Logger.getLogger(this.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public char[] gerarHashSenhaPBKDF2(String senha) throws NoSuchAlgorithmException, InvalidKeySpecException {
        // PBKDF2 with SHA-1 as the hashing algorithm. Note that the NIST
        // specifically names SHA-1 as an acceptable hashing algorithm for PBKDF2
        String algorithm = "PBKDF2WithHmacSHA1";
        // SHA-1 generates 160 bit hashes, so that's what makes sense here
        int derivedKeyLength = 160;
        // Pick an iteration count that works for you. The NIST recommends at
        // least 1,000 iterations:
        // http://csrc.nist.gov/publications/nistpubs/800-132/nist-sp800-132.pdf
        // iOS 4.x reportedly uses 10,000:
        // http://blog.crackpassword.com/2010/09/smartphone-forensics-cracking-blackberry-backup-passwords/
        int iterations = 2000;

        // SALT (EM SITUACOES REAIS, DEVEM SER DIFERENTES PARA CADA USUARIO)
        // Normalmente, deve ser alguma informação que, após cadastrado, não pode mais ser alterado.
        String salt = "AT" + this.nome + "AT";

        KeySpec spec = new PBEKeySpec(senha.toCharArray(), salt.getBytes(), iterations, derivedKeyLength);
        SecretKeyFactory f = SecretKeyFactory.getInstance(algorithm);

        byte[] code = f.generateSecret(spec).getEncoded();

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < code.length; i++) {
          sb.append(Integer.toHexString(0xff & code[i]));
        }
        System.out.println(sb.toString());
        return sb.toString().toCharArray();
    }
    
    public boolean autenticar(String nome, String senha) {
        if (this.nome != null) {
          try {
            return this.nome.equals(nome) && Arrays.equals(this.hashSenha, gerarHashSenhaPBKDF2(senha));
          } catch (NoSuchAlgorithmException | InvalidKeySpecException ex) {
            Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
          }
        }
        return false;
    }
    
    public boolean autorizado(int perfilIdNecessario) {
        return this.codigoPerfil == perfilIdNecessario;
    }
}
