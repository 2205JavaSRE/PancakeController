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


CREATE TABLE public.history (
	acctnum int4 NULL,
	username varchar(15) NULL,
	balance numeric(20, 2) NULL,
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

	 
COMMIT;
