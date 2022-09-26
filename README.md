---
title: Connecting Eclipse Dirigible with SendGrid SMTP Relay
description: In this article we are going to setup and use AWS QLDB with Eclipse Dirigible's QLDB API.
author: Ivo Yakov
author_gh_user: https://github.com/Fluctuationqt/
author_avatar: https://avatars.githubusercontent.com/u/19828259?v=4
read_time: 15 min
publish_date: September 26, 2022
---

# What is QLDB (Quantum Ledger Database)
- What is QLDB?
- Why is it useful for Eclipse Dirigible projects?

## Getting Started Guide
### 1. Setup AWS Account with QLDB Enabled

- [Sign up for AWS](https://portal.aws.amazon.com/billing/signup) or use an existing account.

- Create an AWS Identity and Access Management (IAM) user:
  - Sign in to the [IAM console](https://console.aws.amazon.com/iam/) as the account owner by choosing Root user and entering your AWS account email address. On the next page, enter your password.
  - In the navigation pane, choose Users and then choose Add users.
  - For User name, enter `dirigible_qldb_user`.
  - Select the check box next to AWS Management Console access. Then select `Password - AWS Management Console access` and then enter your new user password in the text box.
  - (Optional) By default, AWS requires the new user to create a new password when first signing in. You can clear the check box next to User must create a new password at next sign-in to allow the new user to reset their password after they sign in.
  - Choose Next: `Permissions.`
  - Under `Set permissions`, choose `Add user to group`.
  - Choose `Create group`.
  - In the Create group dialog box, for Group name enter `dirigible_qldb_group`.
  - Choose in the `Filter policies` input, search for the term `qldb`.
  - Put checkboxes on `AmazonQLDBReadOnly`, `AmazonQLDBFullAccess` and `AmazonQLDBConsoleFullAccess`.
  - Then click on `Create group`.
  - Back in the list of groups, select the `check box` for your new group. Choose `Refresh` if necessary to see the group in the list.
  - Choose `Next: Tags`.
  - (Optional) Add metadata to the user by attaching tags as key-value pairs. For more information about using tags in IAM, see [Tagging IAM entities](https://docs.aws.amazon.com/IAM/latest/UserGuide/id_tags.html) in the IAM User Guide.
  - Choose `Next: Review` to see the list of group memberships to be added to the new user. When you are ready to proceed, choose `Create user`.
  
  <i>Notice: You can read more on how to Create an IAM user in the AWS doccumentation [here](https://docs.aws.amazon.com/qldb/latest/developerguide/accessing.html#setting-up-create-iam-user).</i>
  
- Get an IAM access key (Used by the QLDB Driver in Eclipse Dirigible to establish connections securely).
  - Go back to the [IAM console](https://console.aws.amazon.com/iam/).
  - In the navigation pane, choose `Users`.
  - Click on the name of the user you created in the previous step.
  - Choose the `Security credentials` tab.
  - In the `Access keys` section, choose `Create access key`.
  - To view the new access key pair, choose Show. You will not have access to the secret access key again after this dialog box closes. 
  - Copy and remember the `Access key ID` and `Secret access key` they will be used in the next step.
    - Your credentials will look something like this:
      ``` 
      Access key ID: AKIAIOSFODNN7EXAMPLE
      Secret access key: wJalrXUtnFEMI/K7MDENG/bPxRfiCYEXAMPLEKEY 
      ```
      </i> Notice: You can read more on how to Get an IAM access key in the AWS documentation [here](https://docs.aws.amazon.com/qldb/latest/developerguide/accessing.html#setting-up-iam-user-access-keys)</i>

- Setup the credentials and region on the machine you are running Eclipse Dirigible.
  -  Create a file `~/.aws/credentials`, where the tilde character (~) represents your home directory:
  ```
  [default] 
  region = eu-west-2
  aws_access_key_id = your_access_key_id
  aws_secret_access_key = your_secret_access_key
  ```
  <i> Notice: Replace (`eu-west-2`, `your_access_key_id`, `your_secret_access_key`) with your credentials from the last step. </i>
  
- Setup a ledger:
  - Sign in to the AWS Management Console, and open the [Amazon QLDB console](https://console.aws.amazon.com/qldb).
  - In the navigation pane, choose `Getting started`.
  - On the Create your first ledger card, choose Create Ledger.
  - For `Ledger information` – The Ledger name should be pre-populated with `vehicle-registration`, change that to `myTestLedger`.
  - For `Permission mode` - choose `Standard – (Recommended)` (A permissions mode that enables access control with finer granularity for ledgers, tables, and PartiQL commands.)
  - For `Encrypt data at rest` – choose `Use AWS owned KMS key` (Use a KMS key that is owned and managed by AWS on your behalf. This is the default option and requires no additional setup.)
  - Tags – (Optional) Add metadata to the ledger by attaching tags as key-value pairs. You can add tags to your ledger to help organize and identify them. For more information, see [Tagging Amazon QLDB resources](https://docs.aws.amazon.com/qldb/latest/developerguide/tagging.html).
  - Choose `Create ledger`.
  - In the list of Ledgers, locate `myTestLedger` and wait for the ledger's status to become Active.
  <i>Notice: You can read more on how to setup a ledger in the AWS QLDB documentation [here](https://docs.aws.amazon.com/qldb/latest/developerguide/getting-started-step-1.html)</i>
  
  ### 2. Setup an Eclipse Dirigible instance with AWS QLDB support
  

  ### 3. Create an Eclipse Dirigible Project with AWS QLDB
  - Create a new Project
  - Add a `qldbSample.js` or `qldbSample.mjs` file to the project.
  - Add the following content to the file: </br>
  :warning: <i> Notice: If you are using a `.mjs` file replace the first line with `import { QLDBRepository } from "@dirigible/qldb"`</i>
  ```
  const QLDBRepository = require("qldb/QLDBRepository"); 

  // 1. Create a repository for the ledger 'myTestLedger' that works with a table 'tableName'
  const qldb = new QLDBRepository("myTestLedger", "tableName");

  // 2. [OPTIONAL] Create the table if it doesn't exist in your ledger, otherwise skip this
  qldb.createTable();

  // 3. Insert a JS Object as a record.
  let inserted = qldb.insert({
      email: "test@mail.com",
      number: 123,
      status: false
  });

  // Notice: The inserted object now has a 'documentId' property, 
  // which is the id of the record generated by QLDB
  console.log("Inserted entry: " + inserted);

  // 4.1 Update a record
  inserted.email = "q@mail.com";
  inserted.number = 5;
  inserted.status = false;
  let updated = qldb.update(inserted);
  console.log("Updated entry: " + updated);

  // 4.2 Update a record with an object
  let updatedRaw = qldb.update({
      email: "text@mail.com",
      number: 50000,
      status: true,
      documentId: "7ekJBB1FEm1EmhJBqH0WLX"
  });
  // Notice: Unlike insertion where the 'documentId' is generated by QLDB,
  // in 'update' the object must have a 'documentId' property defined.

  // 5.1 Delete a record 
  let deletedId = qldb.delete(updated);
  console.log("Deleted entry with id: " + deletedId);

  // 5.2 Delete a record by ID
  deletedId = qldb.delete(updated.documentId);
  console.log("Deleted entry with id: " + deletedId);

  // 6. Get array with all transactions for the table
  let transactionHistory = qldb.getHistory();
  console.log("Transaction History:" + transactionHistory);

  // 7. Drop the table
  qldb.dropTable();
  // Notice: In QLDB dropping a table simply inactivates it, 
  // you can reactivate a table that you have dropped by running
  // an SQL UNDROP statement in PartiQL
  ```

### 4. [Optional] Manually Run PartiQL queries against your ledger
- Sign in to the AWS Management Console, and open the [Amazon QLDB console](https://console.aws.amazon.com/qldb).
- In the navigation pane choose `PartiQL editor`.
- In the editor's `Choose a ledger` dropdown select `myTestLedger`.
- You can now write and execute queries to your database manually. 
<i> Notice: This can be useful if you want to create, delete or restore deleted tables. </i>
