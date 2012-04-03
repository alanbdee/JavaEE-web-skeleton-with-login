package boundary;

import entities.Agents;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.Metamodel;
import org.apache.commons.codec.binary.Base64;

/**
 *
 * @author Alan
 * Managed all direct access to the Agents entity.
 */
@Stateless
public class AgentsFacade extends AbstractFacade<Agents> {

    public static final int ITERATION_NUMBER = 1000;
    @PersistenceContext(unitName = "REMPU")
    private EntityManager em;


    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    @Override
    public EntityManager getEntityManager() {
        return em;
    }

    public AgentsFacade() {
        super(Agents.class);
    }
    /**
     * gets an agent based off the agent's username
     * @param username String The username
     * @return Agent Returns found agent or null if not found.
     */
    public Agents getUser(String username) {
        Agents result = null;
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Agents> cq = cb.createQuery(Agents.class);
        Root<Agents> agent = cq.from(Agents.class);
        cq.select(agent);
        Metamodel m = em.getMetamodel();
        cq.where(cb.equal(agent.get("username"), username));
        TypedQuery<Agents> q = em.createQuery(cq);
        try {
            result = q.getSingleResult();
        } catch (NoResultException e) {
            //no results is a perfectally normal situation.
        }
        return result;
    }
    /**
     * Persist an agent to the database
     * Takes the password that has been set and hashes it.
     * If password and/or login is null then always returns false.
     * If the user does not exist in the database returns false.
     * @param login String The login of the user
     * @param password String The password of the user
     * @return boolean Returns true if the user is authenticated, false otherwise
     */
    @Override
    public void create(Agents entity) {
        java.sql.Date d = new java.sql.Date(new java.util.Date().getTime());
        try {
            String username = entity.getUsername();
            String password = entity.getPassword();
                    
            if (username != null && password != null && username.length() <= 100 && password.length() <= 100) {
                // Uses a secure Random not a simple Random
                SecureRandom random;
                random = SecureRandom.getInstance("SHA1PRNG");
                // Salt generation 64 bits long
                byte[] bSalt = new byte[8];
                random.nextBytes(bSalt);
                // Digest computation
                byte[] bDigest = getHash(ITERATION_NUMBER, password, bSalt);
                String sDigest = Base64.encodeBase64String(bDigest);
                String sSalt = Base64.encodeBase64String(bSalt);
                entity.setPassword(sDigest);
                entity.setSalt(sSalt);
            }
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(AgentsFacade.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(AgentsFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        entity.setCreated(d);
        super.create(entity);
    }

    /**
     * Authenticates the user with a given login and password
     * If password and/or login is null then always returns false.
     * If the user does not exist in the database returns false.
     * @param login String The login of the user
     * @param password String The password of the user
     * @return boolean Returns true if the user is authenticated, false otherwise
     * @throws SQLException If the database is inconsistent or unavailable (
     *           (Two users with the same login, salt or digested password altered etc.)
     * @throws NoSuchAlgorithmException If the algorithm SHA-1 is not supported by the JVM
     */
    public boolean authenticate(String login, String password)
            throws NoSuchAlgorithmException, UnsupportedEncodingException {
        boolean authenticated = false;
        boolean userExist = true;
        // INPUT VALIDATION
        if (login == null || password == null) {
            // TIME RESISTANT ATTACK
            // Computation time is equal to the time needed by a legitimate user
            userExist = false;
            login = "";
            password = "";
        }
        Agents user = this.getUser(login);
        String digest, salt;
        if (user == null) {
            // TIME RESISTANT ATTACK (Even if the user does not exist the
            // Computation time is equal to the time needed for a legitimate user
            digest = "000000110000000000000000000=";
            salt = "00000001100=";
            userExist = false;
        } else {
            digest = user.getPassword();
            salt = user.getSalt();
        }
        byte[] bDigest = Base64.decodeBase64(digest);
        byte[] bSalt = Base64.decodeBase64(salt);
        // Compute the new DIGEST
        byte[] proposedDigest = getHash(ITERATION_NUMBER, password, bSalt);

        boolean result = Arrays.equals(proposedDigest, bDigest) && userExist;
        return result;
    }

    /**
     * From a password, a number of iterations and a salt,
     * returns the corresponding digest
     * @param iterationNb int The number of iterations of the algorithm
     * @param password String The password to encrypt
     * @param salt byte[] The salt
     * @return byte[] The digested password
     * @throws NoSuchAlgorithmException If the algorithm doesn't exist
     */
    public byte[] getHash(int iterationNb, String password, byte[] salt)
            throws UnsupportedEncodingException, NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-1");
        digest.reset();
        digest.update(salt);
        byte[] input = digest.digest(password.getBytes("UTF-8"));
        for (int i = 0; i < iterationNb; i++) {
            digest.reset();
            input = digest.digest(input);
        }
        return input;
    }
}
