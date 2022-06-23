BEGIN;

-- public.accounts definition

-- Drop table

-- DROP TABLE public.accounts;

CREATE TABLE public.accounts (
	acctnum int4 NULL,
	username varchar(15) NULL,
	balance numeric(20, 2) NULL,
	"timestamp" timestamp NULL DEFAULT CURRENT_TIMESTAMP
);


-- public.admins definition

-- Drop table

-- DROP TABLE public.admins;

CREATE TABLE public.admins (
	username varchar(30) NULL,
	newrequest bpchar(10) NULL,
	amount numeric(5, 2) NULL,
	approved bool NULL
);


-- public.employee definition

-- Drop table

-- DROP TABLE public.employee;

CREATE TABLE public.employee (
	username varchar(30) NULL,
	upassword varchar(30) NULL,
	ismanager bool NULL
);


-- public.history definition

-- Drop table

-- DROP TABLE public.history;

CREATE TABLE public.history (
	acctnum int4 NULL,
	username varchar(15) NULL,
	balance numeric(20, 2) NULL,
	"timestamp" timestamp NULL DEFAULT CURRENT_TIMESTAMP
);


-- public.requests definition

-- Drop table

-- DROP TABLE public.requests;

CREATE TABLE public.requests (
	username varchar(30) NULL,
	newrequest bpchar(10) NULL,
	amount numeric(5, 2) NULL,
	pending bool NULL,
	approved bool NULL,
	"timestamp" timestamp NULL DEFAULT CURRENT_TIMESTAMP
);


-- public.users definition

-- Drop table

-- DROP TABLE public.users;

CREATE TABLE public.users (
	username varchar(15) NULL,
	"password" varchar(15) NULL,
	ismanager bool NULL
);

INSERT INTO public.accounts (acctnum,username,balance,"timestamp") VALUES
	 (7654321,'larry',1639.20,'2022-06-13 21:54:55.680416'),
	 (6019344,'frank',250.25,'2022-06-14 09:28:35.624888'),
	 (9775977,'john',46.25,'2022-06-14 10:18:46.385002'),
	 (1234567,'jason',8686.73,'2022-06-13 21:54:26.595368');
INSERT INTO public.employee (username,upassword,ismanager) VALUES
	 ('larry','larry',false),
	 ('jason','password',true),
	 ('frank','frank',false);
INSERT INTO public.history (acctnum,username,balance,"timestamp") VALUES
	 (7654321,'larry',1639.20,'2022-06-15 10:59:22.56238'),
	 (6019344,'frank',250.25,'2022-06-15 10:59:22.601533'),
	 (9775977,'john',46.25,'2022-06-15 10:59:22.631351'),
	 (1234567,'jason',8697.21,'2022-06-15 10:59:22.672808'),
	 (7654321,'larry',1639.20,'2022-06-15 11:10:37.13251'),
	 (6019344,'frank',250.25,'2022-06-15 11:10:37.166321'),
	 (9775977,'john',46.25,'2022-06-15 11:10:37.196466'),
	 (1234567,'jason',8691.88,'2022-06-15 11:10:37.228222'),
	 (7654321,'larry',1639.20,'2022-06-15 11:12:33.418728'),
	 (6019344,'frank',250.25,'2022-06-15 11:12:33.443469');
INSERT INTO public.history (acctnum,username,balance,"timestamp") VALUES
	 (9775977,'john',46.25,'2022-06-15 11:12:33.478472'),
	 (1234567,'jason',8686.55,'2022-06-15 11:12:33.519837'),
	 (7654321,'larry',1639.20,'2022-06-15 18:30:45.071732'),
	 (6019344,'frank',250.25,'2022-06-15 18:30:45.083113'),
	 (9775977,'john',46.25,'2022-06-15 18:30:45.093032'),
	 (1234567,'jason',8686.73,'2022-06-15 18:30:45.096637');
INSERT INTO public.requests (username,newrequest,amount,pending,approved,"timestamp") VALUES
	 ('larry','FOOD      ',15.23,false,true,'2022-06-09 19:49:33.021864'),
	 ('larry','OTHER     ',85.22,false,true,'2022-06-10 00:22:45.402545'),
	 ('larry','LODGING   ',65.25,false,true,'2022-06-09 19:49:33.021864'),
	 ('larry','FOOD      ',12.65,false,true,'2022-06-10 15:48:59.386075'),
	 ('frank','FOOD      ',15.60,false,false,'2022-06-10 00:18:44.772547'),
	 ('jason','LODGING   ',55.35,true,false,'2022-06-17 15:49:19.066201');
INSERT INTO public.users (username,"password",ismanager) VALUES
	 ('larry','larry',NULL),
	 ('jason','password',NULL),
	 ('frank','frank',NULL),
	 ('john','john',NULL),
	 ('manager','manager',true);
	 
	 
COMMIT;
