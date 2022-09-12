# Connecting Eclipse Dirigible with SendGrid

- Create a SendGrid account at https://app.sendgrid.com/
- Login at https://app.sendgrid.com/
- Verify a single sender email
  - Click `Settings` > `Sender Authentication` > `Verify a Single Sender`
  - Enter the details of the email address that Dirigible mails will be sent from.
- Setup SMTP Relay
  - Click `Email API` > `Integration Guide` > `SMTP Relay`
  - Enter an API Key Name and click `Create Key` to get an API Key for your SendGrid SMTP Relay.
  - Notice the `Configure your application` section. The details from it will be your `dirigible.properties` configuration
- Setup Dirigible Properties
  - Add the following entries to your dirigible.properties:
    ```
    DIRIGIBLE_MAIL_USERNAME=apikey
    DIRIGIBLE_MAIL_PASSWORD=<YOUR_API_KEY_HERE/>
    DIRIGIBLE_MAIL_TRANSPORT_PROTOCOL=smtps
    DIRIGIBLE_MAIL_SMTPS_HOST=smtp.sendgrid.net
    DIRIGIBLE_MAIL_SMTPS_PORT=465
    ```
- Send an email by running this script
    ```
    import { client as mail } from "@dirigible/mail";
    
    const from = "<YOUR_VERIFIED_SENDER_EMAIL_HERE/>";
    const to = "<YOUR_RECIPIENT_EMAIL_HERE/>";
    const subject = "Subject";
    const content = "Hello World!";
    const subType = "html";
    
    mail.send(from, to, subject, content, subType);
    ```
