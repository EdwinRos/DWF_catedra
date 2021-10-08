package sv.udb.edu.catedraframeworks.utils;

import java.util.Properties;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Emails {

    public void docotorEmail(String destinatario, String usuario, String Drpassword, String nombres, String apellidos) throws MessagingException {
        String nombreApellido = nombres + " " + apellidos;
        String drContra = Drpassword;
        String usser = usuario;

        String correoDestino = destinatario;
        String host = "smtp.gmail.com";
        Properties properties = System.getProperties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "587");

        final String myAccountEmail = "hospitallasalud01@gmail.com";
        final String password = "huaqlnpubzphtxdy";

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(myAccountEmail, password);
            }
        });

        Message message = sendDoctorEmail(session, myAccountEmail, correoDestino, usser, nombreApellido, drContra);
        Transport.send(message);

    }

    private static Message sendDoctorEmail(Session session, String myAcount, String destinatario, String usuario, String nombreapellido, String passwordDr) {
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myAcount));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(destinatario));
            message.setSubject("Bienvenido a la familia Hospital la salud: " + nombreapellido);
            message.setContent("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional //EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\n" +
                    "<html xmlns=\"http://www.w3.org/1999/xhtml\" xmlns:v=\"urn:schemas-microsoft-com:vml\" xmlns:o=\"urn:schemas-microsoft-com:office:office\">\n" +
                    "<head>\n" +
                    "<!--[if gte mso 9]>\n" +
                    "<xml>\n" +
                    "  <o:OfficeDocumentSettings>\n" +
                    "    <o:AllowPNG/>\n" +
                    "    <o:PixelsPerInch>96</o:PixelsPerInch>\n" +
                    "  </o:OfficeDocumentSettings>\n" +
                    "</xml>\n" +
                    "<![endif]-->\n" +
                    "  <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n" +
                    "  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                    "  <meta name=\"x-apple-disable-message-reformatting\">\n" +
                    "  <!--[if !mso]><!--><meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\"><!--<![endif]-->\n" +
                    "  <title></title>\n" +
                    "  \n" +
                    "    <style type=\"text/css\">\n" +
                    "      table, td { color: #000000; } @media (max-width: 480px) { #u_content_image_18 .v-text-align { text-align: center !important; } #u_content_text_1 .v-container-padding-padding { padding: 15px 10px 35px !important; } #u_content_text_1 .v-text-align { text-align: center !important; } #u_content_image_1 .v-src-width { width: 164px !important; } #u_content_image_1 .v-src-max-width { max-width: 20% !important; } }\n" +
                    "@media only screen and (min-width: 620px) {\n" +
                    "  .u-row {\n" +
                    "    width: 600px !important;\n" +
                    "  }\n" +
                    "  .u-row .u-col {\n" +
                    "    vertical-align: top;\n" +
                    "  }\n" +
                    "\n" +
                    "  .u-row .u-col-50 {\n" +
                    "    width: 300px !important;\n" +
                    "  }\n" +
                    "\n" +
                    "  .u-row .u-col-100 {\n" +
                    "    width: 600px !important;\n" +
                    "  }\n" +
                    "\n" +
                    "}\n" +
                    "\n" +
                    "@media (max-width: 620px) {\n" +
                    "  .u-row-container {\n" +
                    "    max-width: 100% !important;\n" +
                    "    padding-left: 0px !important;\n" +
                    "    padding-right: 0px !important;\n" +
                    "  }\n" +
                    "  .u-row .u-col {\n" +
                    "    min-width: 320px !important;\n" +
                    "    max-width: 100% !important;\n" +
                    "    display: block !important;\n" +
                    "  }\n" +
                    "  .u-row {\n" +
                    "    width: calc(100% - 40px) !important;\n" +
                    "  }\n" +
                    "  .u-col {\n" +
                    "    width: 100% !important;\n" +
                    "  }\n" +
                    "  .u-col > div {\n" +
                    "    margin: 0 auto;\n" +
                    "  }\n" +
                    "}\n" +
                    "body {\n" +
                    "  margin: 0;\n" +
                    "  padding: 0;\n" +
                    "}\n" +
                    "\n" +
                    "table,\n" +
                    "tr,\n" +
                    "td {\n" +
                    "  vertical-align: top;\n" +
                    "  border-collapse: collapse;\n" +
                    "}\n" +
                    "\n" +
                    "p {\n" +
                    "  margin: 0;\n" +
                    "}\n" +
                    "\n" +
                    ".ie-container table,\n" +
                    ".mso-container table {\n" +
                    "  table-layout: fixed;\n" +
                    "}\n" +
                    "\n" +
                    "* {\n" +
                    "  line-height: inherit;\n" +
                    "}\n" +
                    "\n" +
                    "a[x-apple-data-detectors='true'] {\n" +
                    "  color: inherit !important;\n" +
                    "  text-decoration: none !important;\n" +
                    "}\n" +
                    "\n" +
                    "</style>\n" +
                    "  \n" +
                    "  \n" +
                    "\n" +
                    "<!--[if !mso]><!--><link href=\"https://fonts.googleapis.com/css?family=Lato:400,700&display=swap\" rel=\"stylesheet\" type=\"text/css\"><!--<![endif]-->\n" +
                    "\n" +
                    "</head>\n" +
                    "\n" +
                    "<body class=\"clean-body u_body\" style=\"margin: 0;padding: 0;-webkit-text-size-adjust: 100%;background-color: #535353;color: #000000\">\n" +
                    "  <!--[if IE]><div class=\"ie-container\"><![endif]-->\n" +
                    "  <!--[if mso]><div class=\"mso-container\"><![endif]-->\n" +
                    "  <table style=\"border-collapse: collapse;table-layout: fixed;border-spacing: 0;mso-table-lspace: 0pt;mso-table-rspace: 0pt;vertical-align: top;min-width: 320px;Margin: 0 auto;background-color: #535353;width:100%\" cellpadding=\"0\" cellspacing=\"0\">\n" +
                    "  <tbody>\n" +
                    "  <tr style=\"vertical-align: top\">\n" +
                    "    <td style=\"word-break: break-word;border-collapse: collapse !important;vertical-align: top\">\n" +
                    "    <!--[if (mso)|(IE)]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td align=\"center\" style=\"background-color: #535353;\"><![endif]-->\n" +
                    "    \n" +
                    "\n" +
                    "<div class=\"u-row-container\" style=\"padding: 25px 0px 0px;background-color: transparent\">\n" +
                    "  <div class=\"u-row\" style=\"Margin: 0 auto;min-width: 320px;max-width: 600px;overflow-wrap: break-word;word-wrap: break-word;word-break: break-word;background-color: #ffffff;\">\n" +
                    "    <div style=\"border-collapse: collapse;display: table;width: 100%;background-color: transparent;\">\n" +
                    "      <!--[if (mso)|(IE)]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"padding: 25px 0px 0px;background-color: transparent;\" align=\"center\"><table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"width:600px;\"><tr style=\"background-color: #ffffff;\"><![endif]-->\n" +
                    "      \n" +
                    "<!--[if (mso)|(IE)]><td align=\"center\" width=\"300\" style=\"width: 300px;padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;\" valign=\"top\"><![endif]-->\n" +
                    "<div class=\"u-col u-col-50\" style=\"max-width: 320px;min-width: 300px;display: table-cell;vertical-align: top;\">\n" +
                    "  <div style=\"width: 100% !important;\">\n" +
                    "  <!--[if (!mso)&(!IE)]><!--><div style=\"padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;\"><!--<![endif]-->\n" +
                    "  \n" +
                    "<table id=\"u_content_image_18\" style=\"font-family:arial,helvetica,sans-serif;\" role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\">\n" +
                    "  <tbody>\n" +
                    "    <tr>\n" +
                    "      <td class=\"v-container-padding-padding\" style=\"overflow-wrap:break-word;word-break:break-word;padding:14px 10px;font-family:arial,helvetica,sans-serif;\" align=\"left\">\n" +
                    "        \n" +
                    "<table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">\n" +
                    "  <tr>\n" +
                    "    <td class=\"v-text-align\" style=\"padding-right: 0px;padding-left: 0px;\" align=\"left\">\n" +
                    "      \n" +
                    "      <img align=\"left\" border=\"0\" src=\"https://images.pexels.com/photos/7723513/pexels-photo-7723513.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940\" alt=\"Logo\" title=\"Logo\" style=\"outline: none;text-decoration: none;-ms-interpolation-mode: bicubic;clear: both;display: inline-block !important;border: none;height: auto;float: none;width: 17%;max-width: 47.6px;\" width=\"47.6\" class=\"v-src-width v-src-max-width\"/>\n" +
                    "      \n" +
                    "    </td>\n" +
                    "  </tr>\n" +
                    "</table>\n" +
                    "\n" +
                    "      </td>\n" +
                    "    </tr>\n" +
                    "  </tbody>\n" +
                    "</table>\n" +
                    "\n" +
                    "  <!--[if (!mso)&(!IE)]><!--></div><!--<![endif]-->\n" +
                    "  </div>\n" +
                    "</div>\n" +
                    "<!--[if (mso)|(IE)]></td><![endif]-->\n" +
                    "<!--[if (mso)|(IE)]><td align=\"center\" width=\"300\" style=\"width: 300px;padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;\" valign=\"top\"><![endif]-->\n" +
                    "<div class=\"u-col u-col-50\" style=\"max-width: 320px;min-width: 300px;display: table-cell;vertical-align: top;\">\n" +
                    "  <div style=\"width: 100% !important;\">\n" +
                    "  <!--[if (!mso)&(!IE)]><!--><div style=\"padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;\"><!--<![endif]-->\n" +
                    "  \n" +
                    "<table id=\"u_content_text_1\" style=\"font-family:arial,helvetica,sans-serif;\" role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\">\n" +
                    "  <tbody>\n" +
                    "    <tr>\n" +
                    "      <td class=\"v-container-padding-padding\" style=\"overflow-wrap:break-word;word-break:break-word;padding:30px 10px 10px;font-family:arial,helvetica,sans-serif;\" align=\"left\">\n" +
                    "        \n" +
                    "  <div class=\"v-text-align\" style=\"line-height: 140%; text-align: right; word-wrap: break-word;\">\n" +
                    "    <p style=\"font-size: 14px; line-height: 140%;\"><span style=\"color: #e03e2d; font-size: 14px; line-height: 19.6px;\">Hospital la Salud</span></p>\n" +
                    "  </div>\n" +
                    "\n" +
                    "      </td>\n" +
                    "    </tr>\n" +
                    "  </tbody>\n" +
                    "</table>\n" +
                    "\n" +
                    "  <!--[if (!mso)&(!IE)]><!--></div><!--<![endif]-->\n" +
                    "  </div>\n" +
                    "</div>\n" +
                    "<!--[if (mso)|(IE)]></td><![endif]-->\n" +
                    "      <!--[if (mso)|(IE)]></tr></table></td></tr></table><![endif]-->\n" +
                    "    </div>\n" +
                    "  </div>\n" +
                    "</div>\n" +
                    "\n" +
                    "\n" +
                    "\n" +
                    "<div class=\"u-row-container\" style=\"padding: 0px;background-color: transparent\">\n" +
                    "  <div class=\"u-row\" style=\"Margin: 0 auto;min-width: 320px;max-width: 600px;overflow-wrap: break-word;word-wrap: break-word;word-break: break-word;background-color: transparent;\">\n" +
                    "    <div style=\"border-collapse: collapse;display: table;width: 100%;background-image: url('https://www.tekcrispy.com/wp-content/uploads/2017/08/Resonancia-Magnetica.jpg');background-repeat: repeat;background-position: center top;background-color: transparent;\">\n" +
                    "      <!--[if (mso)|(IE)]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"padding: 0px;background-color: transparent;\" align=\"center\"><table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"width:600px;\"><tr style=\"background-image: url('images/image-2.jpeg');background-repeat: repeat;background-position: center top;background-color: transparent;\"><![endif]-->\n" +
                    "      \n" +
                    "<!--[if (mso)|(IE)]><td align=\"center\" width=\"600\" style=\"width: 600px;padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;\" valign=\"top\"><![endif]-->\n" +
                    "<div class=\"u-col u-col-100\" style=\"max-width: 320px;min-width: 600px;display: table-cell;vertical-align: top;\">\n" +
                    "  <div style=\"width: 100% !important;\">\n" +
                    "  <!--[if (!mso)&(!IE)]><!--><div style=\"padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;\"><!--<![endif]-->\n" +
                    "  \n" +
                    "<table style=\"font-family:arial,helvetica,sans-serif;\" role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\">\n" +
                    "  <tbody>\n" +
                    "    <tr>\n" +
                    "      <td class=\"v-container-padding-padding\" style=\"overflow-wrap:break-word;word-break:break-word;padding:80px 10px 10px;font-family:arial,helvetica,sans-serif;\" align=\"left\">\n" +
                    "        \n" +
                    "  <div class=\"v-text-align\" style=\"color: #ffffff; line-height: 140%; text-align: center; word-wrap: break-word;\">\n" +
                    "    <p style=\"font-size: 14px; line-height: 140%;\"><span style=\"font-family: Lato, sans-serif; font-size: 20px; line-height: 28px;\">Bienvenido a Hospital la salud</span></p>\n" +
                    "  </div>\n" +
                    "\n" +
                    "      </td>\n" +
                    "    </tr>\n" +
                    "  </tbody>\n" +
                    "</table>\n" +
                    "\n" +
                    "<table style=\"font-family:arial,helvetica,sans-serif;\" role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\">\n" +
                    "  <tbody>\n" +
                    "    <tr>\n" +
                    "      <td class=\"v-container-padding-padding\" style=\"overflow-wrap:break-word;word-break:break-word;padding:10px 30px;font-family:arial,helvetica,sans-serif;\" align=\"left\">\n" +
                    "        \n" +
                    "  <div class=\"v-text-align\" style=\"color: #ffffff; line-height: 170%; text-align: center; word-wrap: break-word;\">\n" +
                    "    <p style=\"font-size: 14px; line-height: 170%;\"><span style=\"font-size: 16px; line-height: 27.2px; font-family: 'trebuchet ms', geneva;\">Es un gusto saludarle \"" + nombreapellido + "\"<br />se detallan su usuario y contrase&ntilde;a para poder</span></p>\n" +
                    "<p style=\"font-size: 14px; line-height: 170%;\"><span style=\"font-size: 16px; line-height: 27.2px; font-family: 'trebuchet ms', geneva;\">ingresar a su perfil de medico, sera un gusto contar con<br />usted en nuestra familia.</span></p>\n" +
                    "<p style=\"font-size: 14px; line-height: 170%;\">&nbsp;</p>\n" +
                    "<p style=\"font-size: 14px; line-height: 170%;\"><strong><span style=\"font-size: 16px; line-height: 27.2px; font-family: 'trebuchet ms', geneva;\">Usuario: \" " + usuario + " \"</span></strong></p>\n" +
                    "<p style=\"font-size: 14px; line-height: 170%;\"><strong><span style=\"font-size: 16px; line-height: 27.2px; font-family: 'trebuchet ms', geneva;\">Contrase&ntilde;a: \" " + passwordDr + " \"</span></strong></p>\n" +
                    "<p style=\"font-size: 14px; line-height: 170%;\">&nbsp;</p>\n" +
                    "<p style=\"font-size: 14px; line-height: 170%;\">&nbsp;</p>\n" +
                    "  </div>\n" +
                    "\n" +
                    "      </td>\n" +
                    "    </tr>\n" +
                    "  </tbody>\n" +
                    "</table>\n" +
                    "\n" +
                    "  <!--[if (!mso)&(!IE)]><!--></div><!--<![endif]-->\n" +
                    "  </div>\n" +
                    "</div>\n" +
                    "<!--[if (mso)|(IE)]></td><![endif]-->\n" +
                    "      <!--[if (mso)|(IE)]></tr></table></td></tr></table><![endif]-->\n" +
                    "    </div>\n" +
                    "  </div>\n" +
                    "</div>\n" +
                    "\n" +
                    "\n" +
                    "\n" +
                    "<div class=\"u-row-container\" style=\"padding: 0px;background-color: transparent\">\n" +
                    "  <div class=\"u-row\" style=\"Margin: 0 auto;min-width: 320px;max-width: 600px;overflow-wrap: break-word;word-wrap: break-word;word-break: break-word;background-color: #a5a5a5;\">\n" +
                    "    <div style=\"border-collapse: collapse;display: table;width: 100%;background-color: transparent;\">\n" +
                    "      <!--[if (mso)|(IE)]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"padding: 0px;background-color: transparent;\" align=\"center\"><table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"width:600px;\"><tr style=\"background-color: #a5a5a5;\"><![endif]-->\n" +
                    "      \n" +
                    "<!--[if (mso)|(IE)]><td align=\"center\" width=\"600\" style=\"width: 600px;padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;\" valign=\"top\"><![endif]-->\n" +
                    "<div class=\"u-col u-col-100\" style=\"max-width: 320px;min-width: 600px;display: table-cell;vertical-align: top;\">\n" +
                    "  <div style=\"width: 100% !important;\">\n" +
                    "  <!--[if (!mso)&(!IE)]><!--><div style=\"padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;\"><!--<![endif]-->\n" +
                    "  \n" +
                    "<table id=\"u_content_image_1\" style=\"font-family:arial,helvetica,sans-serif;\" role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\">\n" +
                    "  <tbody>\n" +
                    "    <tr>\n" +
                    "      <td class=\"v-container-padding-padding\" style=\"overflow-wrap:break-word;word-break:break-word;padding:50px 10px 14px;font-family:arial,helvetica,sans-serif;\" align=\"left\">\n" +
                    "        \n" +
                    "<table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">\n" +
                    "  <tr>\n" +
                    "    <td class=\"v-text-align\" style=\"padding-right: 0px;padding-left: 0px;\" align=\"center\">\n" +
                    "      \n" +
                    "      <img align=\"center\" border=\"0\" src=\"https://uxwing.com/wp-content/themes/uxwing/download/21-medical-science-lab/medical.png\" alt=\"Logo\" title=\"Logo\" style=\"outline: none;text-decoration: none;-ms-interpolation-mode: bicubic;clear: both;display: inline-block !important;border: none;height: auto;float: none;width: 12%;max-width: 69.6px;\" width=\"69.6\" class=\"v-src-width v-src-max-width\"/>\n" +
                    "      \n" +
                    "    </td>\n" +
                    "  </tr>\n" +
                    "</table>\n" +
                    "\n" +
                    "      </td>\n" +
                    "    </tr>\n" +
                    "  </tbody>\n" +
                    "</table>\n" +
                    "\n" +
                    "<table style=\"font-family:arial,helvetica,sans-serif;\" role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\">\n" +
                    "  <tbody>\n" +
                    "    <tr>\n" +
                    "      <td class=\"v-container-padding-padding\" style=\"overflow-wrap:break-word;word-break:break-word;padding:10px;font-family:arial,helvetica,sans-serif;\" align=\"left\">\n" +
                    "        \n" +
                    "<div align=\"center\">\n" +
                    "  <div style=\"display: table; max-width:-1px;\">\n" +
                    "  <!--[if (mso)|(IE)]><table width=\"-1\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"border-collapse:collapse;\" align=\"center\"><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse; mso-table-lspace: 0pt;mso-table-rspace: 0pt; width:-1px;\"><tr><![endif]-->\n" +
                    "  \n" +
                    "    \n" +
                    "    \n" +
                    "    <!--[if (mso)|(IE)]></tr></table></td></tr></table><![endif]-->\n" +
                    "  </div>\n" +
                    "</div>\n" +
                    "\n" +
                    "      </td>\n" +
                    "    </tr>\n" +
                    "  </tbody>\n" +
                    "</table>\n" +
                    "\n" +
                    "<table style=\"font-family:arial,helvetica,sans-serif;\" role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\">\n" +
                    "  <tbody>\n" +
                    "    <tr>\n" +
                    "      <td class=\"v-container-padding-padding\" style=\"overflow-wrap:break-word;word-break:break-word;padding:10px 20px 40px;font-family:arial,helvetica,sans-serif;\" align=\"left\">\n" +
                    "        \n" +
                    "  <div class=\"v-text-align\" style=\"color: #ffffff; line-height: 170%; text-align: center; word-wrap: break-word;\">\n" +
                    "    <p style=\"font-size: 14px; line-height: 170%;\"><span style=\"font-size: 16px; line-height: 27.2px; font-family: 'trebuchet ms', geneva;\">Hospital la Salud</span></p>\n" +
                    "  </div>\n" +
                    "\n" +
                    "      </td>\n" +
                    "    </tr>\n" +
                    "  </tbody>\n" +
                    "</table>\n" +
                    "\n" +
                    "  <!--[if (!mso)&(!IE)]><!--></div><!--<![endif]-->\n" +
                    "  </div>\n" +
                    "</div>\n" +
                    "<!--[if (mso)|(IE)]></td><![endif]-->\n" +
                    "      <!--[if (mso)|(IE)]></tr></table></td></tr></table><![endif]-->\n" +
                    "    </div>\n" +
                    "  </div>\n" +
                    "</div>\n" +
                    "\n" +
                    "\n" +
                    "\n" +
                    "<div class=\"u-row-container\" style=\"padding: 0px;background-color: transparent\">\n" +
                    "  <div class=\"u-row\" style=\"Margin: 0 auto;min-width: 320px;max-width: 600px;overflow-wrap: break-word;word-wrap: break-word;word-break: break-word;background-color: #999999;\">\n" +
                    "    <div style=\"border-collapse: collapse;display: table;width: 100%;background-color: transparent;\">\n" +
                    "      <!--[if (mso)|(IE)]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"padding: 0px;background-color: transparent;\" align=\"center\"><table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"width:600px;\"><tr style=\"background-color: #999999;\"><![endif]-->\n" +
                    "      \n" +
                    "<!--[if (mso)|(IE)]><td align=\"center\" width=\"600\" style=\"width: 600px;padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;\" valign=\"top\"><![endif]-->\n" +
                    "<div class=\"u-col u-col-100\" style=\"max-width: 320px;min-width: 600px;display: table-cell;vertical-align: top;\">\n" +
                    "  <div style=\"width: 100% !important;\">\n" +
                    "  <!--[if (!mso)&(!IE)]><!--><div style=\"padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;\"><!--<![endif]-->\n" +
                    "  \n" +
                    "<table style=\"font-family:arial,helvetica,sans-serif;\" role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\">\n" +
                    "  <tbody>\n" +
                    "    <tr>\n" +
                    "      <td class=\"v-container-padding-padding\" style=\"overflow-wrap:break-word;word-break:break-word;padding:13px 10px;font-family:arial,helvetica,sans-serif;\" align=\"left\">\n" +
                    "        \n" +
                    "  <div class=\"v-text-align\" style=\"color: #e4e4e4; line-height: 170%; text-align: center; word-wrap: break-word;\">\n" +
                    "    <p style=\"font-size: 14px; line-height: 170%;\">&copy;️ 2021 Universidad Don Bosco </p>\n" +
                    "  </div>\n" +
                    "\n" +
                    "      </td>\n" +
                    "    </tr>\n" +
                    "  </tbody>\n" +
                    "</table>\n" +
                    "\n" +
                    "  <!--[if (!mso)&(!IE)]><!--></div><!--<![endif]-->\n" +
                    "  </div>\n" +
                    "</div>\n" +
                    "<!--[if (mso)|(IE)]></td><![endif]-->\n" +
                    "      <!--[if (mso)|(IE)]></tr></table></td></tr></table><![endif]-->\n" +
                    "    </div>\n" +
                    "  </div>\n" +
                    "</div>\n" +
                    "\n" +
                    "\n" +
                    "    <!--[if (mso)|(IE)]></td></tr></table><![endif]-->\n" +
                    "    </td>\n" +
                    "  </tr>\n" +
                    "  </tbody>\n" +
                    "  </table>\n" +
                    "  <!--[if mso]></div><![endif]-->\n" +
                    "  <!--[if IE]></div><![endif]-->\n" +
                    "</body>\n" +
                    "\n" +
                    "</html>", "text/html");
            return message;
        } catch (Exception ex) {
           Logger.getLogger(Emails.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
