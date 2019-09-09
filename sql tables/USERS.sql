--------------------------------------------------------
--  File created - Monday-September-09-2019   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Table USERS
--------------------------------------------------------

  CREATE TABLE "SYSTEM"."USERS" 
   (	"EMAILID" VARCHAR2(30 BYTE), 
	"PASSWORD" VARCHAR2(30 BYTE), 
	"FIRSTNAME" VARCHAR2(30 BYTE), 
	"LASTNAME" VARCHAR2(30 BYTE)
   ) PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM" ;
REM INSERTING into SYSTEM.USERS
SET DEFINE OFF;
Insert into SYSTEM.USERS (EMAILID,PASSWORD,FIRSTNAME,LASTNAME) values ('sailimaye@gmail.com','12345678','sai','limaye');
Insert into SYSTEM.USERS (EMAILID,PASSWORD,FIRSTNAME,LASTNAME) values ('utkarshsharmaas@gmail.com','12345678','utkarsh','sharma');
Insert into SYSTEM.USERS (EMAILID,PASSWORD,FIRSTNAME,LASTNAME) values ('chandaknihal231@gmail.com','12345678','nihal','chandak');
Insert into SYSTEM.USERS (EMAILID,PASSWORD,FIRSTNAME,LASTNAME) values ('19apoorva.v@gmail.com','12345678','apoorva','verma');
Insert into SYSTEM.USERS (EMAILID,PASSWORD,FIRSTNAME,LASTNAME) values ('adit.aggarwal9@gmail.com','12345678','adit','aggarwal');
Insert into SYSTEM.USERS (EMAILID,PASSWORD,FIRSTNAME,LASTNAME) values ('suraj1997pisces@gmail.com','12345678','suraj','hegde');
Insert into SYSTEM.USERS (EMAILID,PASSWORD,FIRSTNAME,LASTNAME) values ('shantanu.vijay8@gmail.com','12345678','shantanu','vijay');
Insert into SYSTEM.USERS (EMAILID,PASSWORD,FIRSTNAME,LASTNAME) values ('ayushinirmal1996@gmail.com','12345678','ayushi','nirmal');
Insert into SYSTEM.USERS (EMAILID,PASSWORD,FIRSTNAME,LASTNAME) values ('joesantosh2210@gmail.com','12345678','joe','nangani');
--------------------------------------------------------
--  DDL for Index SYS_C007059
--------------------------------------------------------

  CREATE UNIQUE INDEX "SYSTEM"."SYS_C007059" ON "SYSTEM"."USERS" ("EMAILID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM" ;
--------------------------------------------------------
--  Constraints for Table USERS
--------------------------------------------------------

  ALTER TABLE "SYSTEM"."USERS" ADD PRIMARY KEY ("EMAILID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM"  ENABLE;
