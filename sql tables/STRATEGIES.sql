--------------------------------------------------------
--  File created - Monday-September-09-2019   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Table STRATEGIES
--------------------------------------------------------

  CREATE TABLE "SYSTEM"."STRATEGIES" 
   (	"STRATEGYNAME" VARCHAR2(30 BYTE), 
	"VIEWS" VARCHAR2(30 BYTE)
   ) PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM" ;
REM INSERTING into SYSTEM.STRATEGIES
SET DEFINE OFF;
Insert into SYSTEM.STRATEGIES (STRATEGYNAME,VIEWS) values ('Collar','Bullish');
Insert into SYSTEM.STRATEGIES (STRATEGYNAME,VIEWS) values ('Long Put','Bearish');
Insert into SYSTEM.STRATEGIES (STRATEGYNAME,VIEWS) values ('Short Call','Bearish');
Insert into SYSTEM.STRATEGIES (STRATEGYNAME,VIEWS) values ('Long Call','Bullish');
Insert into SYSTEM.STRATEGIES (STRATEGYNAME,VIEWS) values ('Short Put','Bullish');
Insert into SYSTEM.STRATEGIES (STRATEGYNAME,VIEWS) values ('Call Spread','Bullish');
Insert into SYSTEM.STRATEGIES (STRATEGYNAME,VIEWS) values ('Put Spread','Bullish');
Insert into SYSTEM.STRATEGIES (STRATEGYNAME,VIEWS) values ('Synthetic Call','Bullish');
Insert into SYSTEM.STRATEGIES (STRATEGYNAME,VIEWS) values ('Long Combo','Bullish');
Insert into SYSTEM.STRATEGIES (STRATEGYNAME,VIEWS) values ('Covered call with futures','Bullish');
Insert into SYSTEM.STRATEGIES (STRATEGYNAME,VIEWS) values ('Call Spread','Bearish');
Insert into SYSTEM.STRATEGIES (STRATEGYNAME,VIEWS) values ('Put Spread','Bearish');
Insert into SYSTEM.STRATEGIES (STRATEGYNAME,VIEWS) values ('Protective Call','Bearish');
Insert into SYSTEM.STRATEGIES (STRATEGYNAME,VIEWS) values ('Covered Put','Bearish');
Insert into SYSTEM.STRATEGIES (STRATEGYNAME,VIEWS) values ('Long Put','Bearish');
Insert into SYSTEM.STRATEGIES (STRATEGYNAME,VIEWS) values ('Short Call','Bearish');
Insert into SYSTEM.STRATEGIES (STRATEGYNAME,VIEWS) values ('Call Spread','Bearish');
Insert into SYSTEM.STRATEGIES (STRATEGYNAME,VIEWS) values ('Put Spread','Bearish');
Insert into SYSTEM.STRATEGIES (STRATEGYNAME,VIEWS) values ('Protective Call','Bearish');
Insert into SYSTEM.STRATEGIES (STRATEGYNAME,VIEWS) values ('Covered Put','Bearish');
Insert into SYSTEM.STRATEGIES (STRATEGYNAME,VIEWS) values (null,'Neutral');
Insert into SYSTEM.STRATEGIES (STRATEGYNAME,VIEWS) values (null,'Neutral');
Insert into SYSTEM.STRATEGIES (STRATEGYNAME,VIEWS) values (null,'Neutral');
Insert into SYSTEM.STRATEGIES (STRATEGYNAME,VIEWS) values (null,'Neutral');
Insert into SYSTEM.STRATEGIES (STRATEGYNAME,VIEWS) values (null,'Neutral');
Insert into SYSTEM.STRATEGIES (STRATEGYNAME,VIEWS) values (null,'Neutral');
Insert into SYSTEM.STRATEGIES (STRATEGYNAME,VIEWS) values (null,'Neutral');
Insert into SYSTEM.STRATEGIES (STRATEGYNAME,VIEWS) values (null,'Neutral');
Insert into SYSTEM.STRATEGIES (STRATEGYNAME,VIEWS) values (null,'Neutral');
Insert into SYSTEM.STRATEGIES (STRATEGYNAME,VIEWS) values ('Long Put','Bearish');
Insert into SYSTEM.STRATEGIES (STRATEGYNAME,VIEWS) values ('Short Call','Bearish');
Insert into SYSTEM.STRATEGIES (STRATEGYNAME,VIEWS) values ('Call Spread','Bearish');
Insert into SYSTEM.STRATEGIES (STRATEGYNAME,VIEWS) values ('Put Spread','Bearish');
Insert into SYSTEM.STRATEGIES (STRATEGYNAME,VIEWS) values ('Protective Call','Bearish');
Insert into SYSTEM.STRATEGIES (STRATEGYNAME,VIEWS) values ('Covered Put','Bearish');
Insert into SYSTEM.STRATEGIES (STRATEGYNAME,VIEWS) values ('Long Straddle','Neutral');
Insert into SYSTEM.STRATEGIES (STRATEGYNAME,VIEWS) values ('Short Straddle','Neutral');
Insert into SYSTEM.STRATEGIES (STRATEGYNAME,VIEWS) values (null,'Neutral');
Insert into SYSTEM.STRATEGIES (STRATEGYNAME,VIEWS) values (null,'Neutral');
Insert into SYSTEM.STRATEGIES (STRATEGYNAME,VIEWS) values (null,'Neutral');
Insert into SYSTEM.STRATEGIES (STRATEGYNAME,VIEWS) values (null,'Neutral');
Insert into SYSTEM.STRATEGIES (STRATEGYNAME,VIEWS) values (null,'Neutral');
Insert into SYSTEM.STRATEGIES (STRATEGYNAME,VIEWS) values (null,'Neutral');
Insert into SYSTEM.STRATEGIES (STRATEGYNAME,VIEWS) values (null,'Neutral');
Insert into SYSTEM.STRATEGIES (STRATEGYNAME,VIEWS) values ('Long Put','Bearish');
Insert into SYSTEM.STRATEGIES (STRATEGYNAME,VIEWS) values ('Short Call','Bearish');
Insert into SYSTEM.STRATEGIES (STRATEGYNAME,VIEWS) values ('Call Spread','Bearish');
Insert into SYSTEM.STRATEGIES (STRATEGYNAME,VIEWS) values ('Put Spread','Bearish');
Insert into SYSTEM.STRATEGIES (STRATEGYNAME,VIEWS) values ('Protective Call','Bearish');
Insert into SYSTEM.STRATEGIES (STRATEGYNAME,VIEWS) values ('Covered Put','Bearish');
Insert into SYSTEM.STRATEGIES (STRATEGYNAME,VIEWS) values ('Long Straddle','Neutral');
Insert into SYSTEM.STRATEGIES (STRATEGYNAME,VIEWS) values ('Short Straddle','Neutral');
Insert into SYSTEM.STRATEGIES (STRATEGYNAME,VIEWS) values ('Long Strangle','Neutral');
Insert into SYSTEM.STRATEGIES (STRATEGYNAME,VIEWS) values ('Short Strangle','Neutral');
Insert into SYSTEM.STRATEGIES (STRATEGYNAME,VIEWS) values (null,'Neutral');
Insert into SYSTEM.STRATEGIES (STRATEGYNAME,VIEWS) values (null,'Neutral');
Insert into SYSTEM.STRATEGIES (STRATEGYNAME,VIEWS) values (null,'Neutral');
Insert into SYSTEM.STRATEGIES (STRATEGYNAME,VIEWS) values (null,'Neutral');
Insert into SYSTEM.STRATEGIES (STRATEGYNAME,VIEWS) values (null,'Neutral');
Insert into SYSTEM.STRATEGIES (STRATEGYNAME,VIEWS) values ('Long Put','Bearish');
Insert into SYSTEM.STRATEGIES (STRATEGYNAME,VIEWS) values ('Short Call','Bearish');
Insert into SYSTEM.STRATEGIES (STRATEGYNAME,VIEWS) values ('Call Spread','Bearish');
Insert into SYSTEM.STRATEGIES (STRATEGYNAME,VIEWS) values ('Put Spread','Bearish');
Insert into SYSTEM.STRATEGIES (STRATEGYNAME,VIEWS) values ('Protective Call','Bearish');
Insert into SYSTEM.STRATEGIES (STRATEGYNAME,VIEWS) values ('Covered Put','Bearish');
Insert into SYSTEM.STRATEGIES (STRATEGYNAME,VIEWS) values ('Long Straddle','Neutral');
Insert into SYSTEM.STRATEGIES (STRATEGYNAME,VIEWS) values ('Short Straddle','Neutral');
Insert into SYSTEM.STRATEGIES (STRATEGYNAME,VIEWS) values ('Long Strangle','Neutral');
Insert into SYSTEM.STRATEGIES (STRATEGYNAME,VIEWS) values ('Short Strangle','Neutral');
Insert into SYSTEM.STRATEGIES (STRATEGYNAME,VIEWS) values ('Long Call Butterfly','Neutral');
Insert into SYSTEM.STRATEGIES (STRATEGYNAME,VIEWS) values ('Short Call Butterfly','Neutral');
Insert into SYSTEM.STRATEGIES (STRATEGYNAME,VIEWS) values (null,'Neutral');
Insert into SYSTEM.STRATEGIES (STRATEGYNAME,VIEWS) values (null,'Neutral');
Insert into SYSTEM.STRATEGIES (STRATEGYNAME,VIEWS) values (null,'Neutral');
Insert into SYSTEM.STRATEGIES (STRATEGYNAME,VIEWS) values ('Long Put','Bearish');
Insert into SYSTEM.STRATEGIES (STRATEGYNAME,VIEWS) values ('Short Call','Bearish');
Insert into SYSTEM.STRATEGIES (STRATEGYNAME,VIEWS) values ('Call Spread','Bearish');
Insert into SYSTEM.STRATEGIES (STRATEGYNAME,VIEWS) values ('Put Spread','Bearish');
Insert into SYSTEM.STRATEGIES (STRATEGYNAME,VIEWS) values ('Protective Call','Bearish');
Insert into SYSTEM.STRATEGIES (STRATEGYNAME,VIEWS) values ('Covered Put','Bearish');
Insert into SYSTEM.STRATEGIES (STRATEGYNAME,VIEWS) values ('Long Straddle','Neutral');
Insert into SYSTEM.STRATEGIES (STRATEGYNAME,VIEWS) values ('Short Straddle','Neutral');
Insert into SYSTEM.STRATEGIES (STRATEGYNAME,VIEWS) values ('Long Strangle','Neutral');
Insert into SYSTEM.STRATEGIES (STRATEGYNAME,VIEWS) values ('Short Strangle','Neutral');
Insert into SYSTEM.STRATEGIES (STRATEGYNAME,VIEWS) values ('Long Call Butterfly','Neutral');
Insert into SYSTEM.STRATEGIES (STRATEGYNAME,VIEWS) values ('Short Call Butterfly','Neutral');
Insert into SYSTEM.STRATEGIES (STRATEGYNAME,VIEWS) values ('Long Call Condor','Neutral');
Insert into SYSTEM.STRATEGIES (STRATEGYNAME,VIEWS) values ('Short Call Condor','Neutral');
Insert into SYSTEM.STRATEGIES (STRATEGYNAME,VIEWS) values ('Long Box/Conversion','Neutral');
Insert into SYSTEM.STRATEGIES (STRATEGYNAME,VIEWS) values ('Long Put','Bearish');
Insert into SYSTEM.STRATEGIES (STRATEGYNAME,VIEWS) values ('Short Call','Bearish');
Insert into SYSTEM.STRATEGIES (STRATEGYNAME,VIEWS) values ('Call Spread','Bearish');
Insert into SYSTEM.STRATEGIES (STRATEGYNAME,VIEWS) values ('Put Spread','Bearish');
Insert into SYSTEM.STRATEGIES (STRATEGYNAME,VIEWS) values ('Protective Call','Bearish');
Insert into SYSTEM.STRATEGIES (STRATEGYNAME,VIEWS) values ('Covered Put','Bearish');
Insert into SYSTEM.STRATEGIES (STRATEGYNAME,VIEWS) values ('Long Straddle','Neutral');
Insert into SYSTEM.STRATEGIES (STRATEGYNAME,VIEWS) values ('Short Straddle','Neutral');
Insert into SYSTEM.STRATEGIES (STRATEGYNAME,VIEWS) values ('Long Strangle','Neutral');
Insert into SYSTEM.STRATEGIES (STRATEGYNAME,VIEWS) values ('Short Strangle','Neutral');
Insert into SYSTEM.STRATEGIES (STRATEGYNAME,VIEWS) values ('Long Call Butterfly','Neutral');
Insert into SYSTEM.STRATEGIES (STRATEGYNAME,VIEWS) values ('Short Call Butterfly','Neutral');
Insert into SYSTEM.STRATEGIES (STRATEGYNAME,VIEWS) values ('Long Call Condor','Neutral');
Insert into SYSTEM.STRATEGIES (STRATEGYNAME,VIEWS) values ('Short Call Condor','Neutral');
Insert into SYSTEM.STRATEGIES (STRATEGYNAME,VIEWS) values ('Long Box/Conversion','Neutral');
