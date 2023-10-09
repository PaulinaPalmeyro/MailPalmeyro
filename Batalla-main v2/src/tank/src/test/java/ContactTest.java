import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import java.util.ArrayList;
import java.util.List;



import battle2023.ucp.Entities.Mailbox;
import battle2023.ucp.Entities.Contact;
import battle2023.ucp.Entities.Email;
import battle2023.ucp.Entities.EmailManager;


public class ContactTest {
    private Contact validContact;
    private Contact invalidContact;

    @Before
    public void setUp() {
        // Creamos un contacto valido
        validContact = new Contact("Contact1", "contact1@ejemplo.com");

        // Creamos un contacto invalido para probar el constructor
        invalidContact = null;
    }

    @Test
    public void testValidContactConstructor() { //Se asegura de que se cree un contacto valido
        assertNotNull(validContact);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidContactConstructor() { //Se asegura que un contacto con mail no valido no funcione
        invalidContact = new Contact("Invalid Contact", "invalidemail");
    }

    @Test
    public void testGetName() {
        assertEquals("Contact1", validContact.getName());
    }

    @Test
    public void testGetEmail() {
        assertEquals("contact1@ejemplo.com", validContact.getEmail());
    }

    @Test 
    public void testValidEmailContainsArroba() {
        try {
            // Validamos una expresion de un mail
            String validEmail = "test@ejemplo.com";
            Contact validContact = new Contact("Valid Contact", validEmail);

            // Accedemos al metodo de isValid para verificar si es valido(es privado)
            Method isValidEmailMethod = Contact.class.getDeclaredMethod("isValidEmail", String.class);
            isValidEmailMethod.setAccessible(true);

            // invocamos el método privado en el objeto validContact 
            boolean isValidValid = (boolean) isValidEmailMethod.invoke(validContact, validEmail);
            assertTrue(isValidValid);

            // Tomamos un mail invalido (falta "@")
            String invalidEmail = "invalidemail";
            try {
                //Tratamos de crear un contacto con el mail inválido, lo que da un error
                new Contact("Invalid Contact", invalidEmail);
            } catch (IllegalArgumentException e) {
                // tomamos el error
                assertTrue(e.getMessage().equals("Invalid email format"));
            }

        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }


}