--Fecha: 19/02/2020
--Nombre: BASE DE DATOS SISTEMA DE MATRICULA

PROMPT Creacion de Tablas
drop table TB_Curso_X_Estudiante;
drop table TB_Curso;
drop table TB_Profesor;
drop table TB_Estudiante;
drop table TB_Persona;
drop table TB_Usuario;
drop table TB_Carrera;
drop table TB_Ciclo;
drop sequence Curs_sec_id;
drop sequence Carr_sec_id;
drop sequence Cicl_sec_id;
drop sequence Pers_sec_id;
drop sequence Prof_sec_id;
drop sequence Estu_sec_id;
drop sequence Usua_sec_id;

create table TB_Carrera(
Carr_id_PK number(3) not null,
Carr_codi varchar2(5) not null,
Carr_nomb varchar2(20) null,
Carr_titu varchar2(20) null
);

create table TB_Ciclo(
Cicl_id_PK number(3) not null,
Cicl_anno number(4) not null,
Cicl_nume number(2) null,
Cicl_fech_inic date null,
Cicl_fech_fina date null
);

create table TB_Curso(
Curs_id_PK number(3) not null,
Curs_codi varchar2(5) not null,
Curs_nomb varchar2(20) null,
Curs_cred number(2) null,
Curs_hora_sema number(2) null,
Curs_anno number(2) null,
Cicl_id_FK number(3) null,
Carr_id_FK number(3) null
);

create table TB_Persona(
Pers_id_PK number(3) not null,
Pers_cedu varchar2(9) null,
Pers_nomb varchar2(20) null,
Pers_ape1 varchar2(20) null,
Pers_ape2 varchar2(20) null,
Pers_tele varchar2(20) null,
Pers_corr varchar2(20) null
);

create table TB_Profesor(
Prof_id_PK number(3) not null,
Pers_id_FK number(3) not null
);

create table TB_Estudiante(
Estu_id_PK number(3) not null,
Pers_id_FK number(3) not null,
Estu_edad number(3) null,
Carr_id_FK number(3) null
);

create table TB_Usuario(
Usua_id_PK number(3) not null,
Usua_usua varchar2(20) not null,
Usua_cont varchar2(40) not null,
Usua_tipo_pers varchar2(2) null,
Usua_id_pers number(3) null
);

create table TB_Curso_X_Estudiante(
Curs_id_FK number(3) not null,
Estu_id_FK number(3) not null
);


PROMPT PKs
alter table TB_Carrera add constraint
TB_Carrera_id_PK primary key (Carr_id_PK);

alter table TB_Ciclo add constraint
TB_Ciclo_id_PK primary key (Cicl_id_PK);

alter table TB_Curso add constraint
TB_Curso_id_PK primary key (Curs_id_PK);

alter table TB_Persona add constraint
TB_Persona_id_PK primary key (Pers_id_PK);

alter table TB_Profesor add constraint
TB_Profesor_id_PK primary key (Prof_id_PK);

alter table TB_Estudiante add constraint
TB_Estudiante_id_PK primary key (Estu_id_PK);

alter table TB_Usuario add constraint
TB_Usuario_id_PK primary key (Usua_id_PK);

alter table TB_Curso_X_Estudiante add constraint
TB_Curso_X_Estudiante_id_PK primary key (Curs_id_FK, Estu_id_FK);


PROMPT FKs
alter table TB_Curso add constraint
TB_Curso_id_FK_TB_Carrera foreign key (Carr_id_FK) references TB_Carrera(Carr_id_PK);

alter table TB_Curso add constraint
TB_Curso_id_FK_TB_Ciclo foreign key (Cicl_id_FK) references TB_Ciclo(Cicl_id_PK);

alter table TB_Profesor add constraint
TB_Profesor_id_FK_TB_Persona foreign key (Pers_id_FK) references TB_Persona(Pers_id_PK) ON DELETE CASCADE;

alter table TB_Estudiante add constraint
TB_Estudiante_id_FK_TB_Persona foreign key (Pers_id_FK) references TB_Persona(Pers_id_PK)  ON DELETE CASCADE;

alter table TB_Estudiante add constraint
TB_Estudiante_id_FK_TB_Carrera foreign key (Carr_id_FK) references TB_Carrera(Carr_id_PK);

alter table TB_Curso_X_Estudiante add constraint
TB_Curs_X_Estu_id_FK_TB_Curso foreign key (Curs_id_FK) references TB_Curso(Curs_id_PK);

alter table TB_Curso_X_Estudiante add constraint
TB_Curs_X_Estu_id_FK_TB_Estu foreign key (Estu_id_FK) references TB_Estudiante(Estu_id_PK);

PROMPT SEQUENCES
create sequence Carr_sec_id  start with 1 increment by 1 maxvalue 1000;
create sequence Cicl_sec_id  start with 1 increment by 1 maxvalue 1000;
create sequence Curs_sec_id  start with 1 increment by 1 maxvalue 1000;
create sequence Pers_sec_id  start with 1 increment by 1 maxvalue 1000;
create sequence Prof_sec_id  start with 1 increment by 1 maxvalue 1000;
create sequence Estu_sec_id  start with 1 increment by 1 maxvalue 1000;
create sequence Usua_sec_id  start with 1 increment by 1 maxvalue 1000;

PROMPT PROCEDURE AND FUNCTION

CREATE OR REPLACE PACKAGE TYPES

AS

TYPE REF_CURSOR IS REF CURSOR;

END;
/

PROMPT INSERT_PROFESOR

create or replace PROCEDURE insert_profesor(Pers_cedu IN TB_Persona.Pers_cedu%TYPE,
Pers_nomb IN TB_Persona.Pers_nomb%TYPE, Pers_ape1 IN TB_Persona.Pers_ape1%TYPE, Pers_ape2 IN TB_Persona.Pers_ape2%TYPE,
Pers_tele IN TB_Persona.Pers_tele%TYPE, Pers_corr IN TB_Persona.Pers_corr%TYPE)

AS

BEGIN

INSERT INTO TB_Persona VALUES(pers_sec_id.nextval, Pers_cedu, Pers_nomb, Pers_ape1, Pers_ape2, Pers_tele, Pers_corr);
INSERT INTO TB_Profesor VALUES(prof_sec_id.nextval, pers_sec_id.currval);

END;

/

PROMPT UPDATE_PROFESOR

create or replace PROCEDURE update_profesor(P_Prof_id_PK IN TB_Profesor.Prof_id_PK%TYPE,
P_Pers_cedu IN TB_Persona.Pers_cedu%TYPE, P_Pers_nomb IN TB_Persona.Pers_nomb%TYPE, P_Pers_ape1 IN TB_Persona.Pers_ape1%TYPE,
P_Pers_ape2 IN TB_Persona.Pers_ape2%TYPE, P_Pers_tele IN TB_Persona.Pers_tele%TYPE, P_Pers_corr IN TB_Persona.Pers_corr%TYPE)

AS

BEGIN

UPDATE TB_Persona SET Pers_cedu = P_Pers_cedu, Pers_nomb = P_Pers_nomb, Pers_ape1 = P_Pers_ape1, Pers_ape2 = P_Pers_ape2, Pers_tele = P_Pers_tele, Pers_corr = P_Pers_corr WHERE Pers_id_PK = (SELECT TB_Profesor.Pers_id_FK FROM TB_Profesor WHERE TB_Profesor.Prof_id_PK = P_Prof_id_PK);

END;

/

PROMPT QUERY_PROFESOR

CREATE OR REPLACE FUNCTION query_profesor(P_Prof_id_PK IN TB_Profesor.Prof_id_PK%TYPE)

RETURN TYPES.REF_CURSOR 

AS 

CURSOR_PROFESOR TYPES.REF_CURSOR; 

BEGIN 

OPEN CURSOR_PROFESOR FOR 

SELECT Prof_id_PK, Pers_cedu, Pers_nomb, Pers_ape1, Pers_ape2, Pers_tele, Pers_corr FROM TB_Profesor, TB_Persona WHERE Prof_id_PK = P_Prof_id_PK AND Pers_id_PK = Pers_id_FK; 

IF SQL%NOTFOUND THEN
	RAISE_APPLICATION_ERROR (-20201,'No se encontró ningún profesor con este identificador.');
END IF;

RETURN CURSOR_PROFESOR; 

END;

/

PROMPT LIST_PROFESOR

CREATE OR REPLACE FUNCTION list_profesor

RETURN TYPES.REF_CURSOR 

AS 

CURSOR_PROFESOR TYPES.REF_CURSOR; 

BEGIN 

OPEN CURSOR_PROFESOR FOR 

SELECT Prof_id_PK, Pers_cedu, Pers_nomb, Pers_ape1, Pers_ape2, Pers_tele, Pers_corr FROM TB_Profesor, TB_Persona WHERE Pers_id_PK = Pers_id_FK;

RETURN CURSOR_PROFESOR; 

END;

/

PROMPT DELETE_PROFESOR

create or replace PROCEDURE delete_profesor(P_Prof_id_PK IN TB_Profesor.Prof_id_PK%TYPE)

AS

BEGIN

DELETE FROM TB_Persona WHERE TB_Persona.Pers_id_PK = (SELECT Pers_id_FK FROM TB_Profesor WHERE Prof_id_PK = P_Prof_id_PK);

IF SQL%NOTFOUND THEN
	RAISE_APPLICATION_ERROR (-20201,'No se encontró ningún profesor para eliminar con este identificador.');
END IF;

END;

/

PROMPT INSERT_ESTUDIANTE

create or replace PROCEDURE insert_estudiante(Pers_nomb IN TB_Persona.Pers_nomb%TYPE,
Pers_ape1 IN TB_Persona.Pers_ape1%TYPE, Pers_ape2 IN TB_Persona.Pers_ape2%TYPE, Estu_edad IN TB_Estudiante.Estu_edad%TYPE)

AS

BEGIN

INSERT INTO TB_Persona VALUES(pers_sec_id.nextval, null, Pers_nomb, Pers_ape1, Pers_ape2, null, null);
INSERT INTO TB_Estudiante VALUES(estu_sec_id.nextval, pers_sec_id.currval, Estu_edad, null);

END;

/

PROMPT UPDATE_ESTUDIANTE

create or replace PROCEDURE update_estudiante(P_Estu_id_PK IN TB_Estudiante.Estu_id_PK%TYPE,
P_Pers_nomb IN TB_Persona.Pers_nomb%TYPE, P_Pers_ape1 IN TB_Persona.Pers_ape1%TYPE, P_Pers_ape2 IN TB_Persona.Pers_ape2%TYPE,
P_Estu_edad IN TB_Estudiante.Estu_edad%TYPE)

AS

BEGIN

UPDATE TB_Persona SET Pers_nomb = P_Pers_nomb, Pers_ape1 = P_Pers_ape1, Pers_ape2 = P_Pers_ape2 WHERE Pers_id_PK = (SELECT TB_Estudiante.Pers_id_FK FROM TB_Estudiante WHERE TB_Estudiante.Estu_id_PK = P_Estu_id_PK);
UPDATE TB_Estudiante SET Estu_edad = P_Estu_edad WHERE Estu_id_PK = P_Estu_id_PK;

END;

/

PROMPT QUERY_ESTUDIANTE

CREATE OR REPLACE FUNCTION query_estudiante(P_Estu_id_PK IN TB_Estudiante.Estu_id_PK%TYPE)

RETURN TYPES.REF_CURSOR 

AS 

CURSOR_ESTUDIANTE TYPES.REF_CURSOR; 

BEGIN 

OPEN CURSOR_ESTUDIANTE FOR 

SELECT Estu_id_PK, Pers_nomb, Pers_ape1, Pers_ape2, Estu_edad FROM TB_Estudiante, TB_Persona WHERE Estu_id_PK = P_Estu_id_PK AND Pers_id_PK = Pers_id_FK;

IF SQL%NOTFOUND THEN
	RAISE_APPLICATION_ERROR (-20201,'No se encontró ningún estudiante con este identificador.');
END IF;

RETURN CURSOR_ESTUDIANTE;

END;

/

PROMPT LIST_ESTUDIANTE

CREATE OR REPLACE FUNCTION list_estudiante

RETURN TYPES.REF_CURSOR 

AS 

CURSOR_ESTUDIANTE TYPES.REF_CURSOR; 

BEGIN 

OPEN CURSOR_ESTUDIANTE FOR 

SELECT Estu_id_PK, Pers_nomb, Pers_ape1, Pers_ape2, Estu_edad FROM TB_Estudiante, TB_Persona WHERE Pers_id_PK = Pers_id_FK; 

RETURN CURSOR_ESTUDIANTE; 

END;

/

PROMPT DELETE_ESTUDIANTE

create or replace PROCEDURE delete_estudiante(P_Estu_id_PK IN TB_Estudiante.Estu_id_PK%TYPE)

AS

BEGIN

DELETE FROM TB_Persona WHERE TB_Persona.Pers_id_PK = (SELECT Pers_id_FK FROM TB_Estudiante WHERE Estu_id_PK = P_Estu_id_PK);

IF SQL%NOTFOUND THEN
	RAISE_APPLICATION_ERROR (-20201,'No se encontró ningún estudiante para eliminar con este identificador.');
END IF;

END;

/

PROMPT INSERT_USUARIO

create or replace PROCEDURE insert_usuario(Usua_usua IN TB_Usuario.Usua_usua%TYPE, Usua_cont IN TB_Usuario.Usua_cont%TYPE,
Usua_tipo_pers IN TB_Usuario.Usua_tipo_pers%TYPE, Usua_id_pers IN TB_Usuario.Usua_id_pers%TYPE)

AS

BEGIN

INSERT INTO TB_Usuario VALUES(Usua_sec_id.nextval, Usua_usua, Usua_cont, Usua_tipo_pers, Usua_id_pers);

END;

/

PROMPT UPDATE_USUARIO

create or replace PROCEDURE update_usuario(P_Usua_id_PK IN TB_Usuario.Usua_id_PK%TYPE, P_Usua_usua IN TB_Usuario.Usua_usua%TYPE,
P_Usua_cont IN TB_Usuario.Usua_cont%TYPE, P_Usua_tipo_pers IN TB_Usuario.Usua_tipo_pers%TYPE, P_Usua_id_pers IN TB_Usuario.Usua_id_pers%TYPE)

AS

BEGIN

UPDATE TB_Usuario SET Usua_usua = P_Usua_usua, Usua_cont = P_Usua_cont, Usua_tipo_pers = P_Usua_tipo_pers, Usua_id_pers = P_Usua_id_pers WHERE Usua_id_PK = P_Usua_id_PK;

END;

/

PROMPT QUERY_USUARIO

CREATE OR REPLACE FUNCTION query_usuario(P_Usua_id_PK IN TB_Usuario.Usua_id_PK%TYPE)

RETURN TYPES.REF_CURSOR 

AS 

CURSOR_USUARIO TYPES.REF_CURSOR; 

BEGIN 

OPEN CURSOR_USUARIO FOR 

SELECT Usua_id_PK, Usua_usua, Usua_cont, Usua_tipo_pers, Usua_id_pers FROM TB_Usuario WHERE Usua_id_PK = P_Usua_id_PK; 

IF SQL%NOTFOUND THEN
	RAISE_APPLICATION_ERROR (-20201,'No se encontró ningún profesor con este identificador.');
END IF;

RETURN CURSOR_USUARIO; 

END;

/

PROMPT LIST_USUARIO

CREATE OR REPLACE FUNCTION list_usuario

RETURN TYPES.REF_CURSOR 

AS 

CURSOR_USUARIO TYPES.REF_CURSOR; 

BEGIN 

OPEN CURSOR_USUARIO FOR 

SELECT Usua_id_PK, Usua_usua, Usua_cont, Usua_tipo_pers, Usua_id_pers FROM TB_Usuario;

RETURN CURSOR_USUARIO; 

END;

/

PROMPT DELETE_USUARIO

create or replace PROCEDURE delete_usuario(P_Usua_id_PK IN TB_Usuario.Usua_id_PK%TYPE)

AS

BEGIN

DELETE FROM TB_Usuario WHERE Usua_id_PK = P_Usua_id_PK;

IF SQL%NOTFOUND THEN
	RAISE_APPLICATION_ERROR (-20201,'No se encontró ningún usuario para eliminar con este identificador.');
END IF;

END;

/
PROMPT LOGIN_USUARIO

CREATE OR REPLACE FUNCTION login_usuario(P_Usua_usua IN TB_Usuario.Usua_usua%TYPE,P_Usua_cont IN TB_Usuario.Usua_cont%TYPE)

RETURN TYPES.REF_CURSOR 

AS 

CURSOR_USUARIO TYPES.REF_CURSOR; 

BEGIN 

OPEN CURSOR_USUARIO FOR 

SELECT Usua_id_PK, Usua_usua, Usua_cont, Usua_tipo_pers, Usua_id_pers FROM TB_Usuario WHERE Usua_usua = P_Usua_usua AND Usua_cont = P_Usua_cont; 

IF SQL%NOTFOUND THEN
	RAISE_APPLICATION_ERROR (-20201,'No se encontró ningún usuario con este usuario y esta contraseña.');
END IF;

RETURN CURSOR_USUARIO; 

END;

/

PROMPT INSERT_CURSO

create or replace PROCEDURE insert_curso(Curs_codi IN TB_Curso.Curs_codi%TYPE,
Curs_nomb IN TB_Curso.Curs_nomb%TYPE, Curs_cred IN TB_Curso.Curs_cred%TYPE, Curs_hora_sema IN TB_Curso.Curs_hora_sema%TYPE,
Curs_anno IN TB_Curso.Curs_anno%TYPE, Cicl_id_FK IN TB_Curso.Cicl_id_FK%TYPE, Carr_id_FK IN TB_Curso.Carr_id_FK%TYPE)

AS

BEGIN

INSERT INTO TB_Curso VALUES(Curs_sec_id.nextval, Curs_codi, Curs_nomb, Curs_cred, Curs_hora_sema, Curs_anno, Cicl_id_FK, Carr_id_FK);

END;

/

PROMPT UPDATE_CURSO

create or replace PROCEDURE update_curso(P_Curs_id_PK IN TB_Curso.Curs_id_PK%TYPE, P_Curs_codi IN TB_Curso.Curs_codi%TYPE,
P_Curs_nomb IN TB_Curso.Curs_nomb%TYPE, P_Curs_cred IN TB_Curso.Curs_cred%TYPE, P_Curs_hora_sema IN TB_Curso.Curs_hora_sema%TYPE,
P_Curs_anno IN TB_Curso.Curs_anno%TYPE, P_Cicl_id_FK IN TB_Curso.Cicl_id_FK%TYPE, P_Carr_id_FK IN TB_Curso.Carr_id_FK%TYPE)

AS

BEGIN

UPDATE TB_Curso SET Curs_codi = P_Curs_codi, Curs_nomb = P_Curs_nomb, Curs_cred = P_Curs_cred, Curs_hora_sema = P_Curs_hora_sema, Curs_anno = P_Curs_anno, Cicl_id_FK = P_Cicl_id_FK, Carr_id_FK = P_Carr_id_FK WHERE Curs_id_PK = P_Curs_id_PK;

END;

/

PROMPT QUERY_CURSO

CREATE OR REPLACE FUNCTION query_curso(P_Curs_id_PK IN TB_Curso.Curs_id_PK%TYPE)

RETURN TYPES.REF_CURSOR 

AS 

CURSOR_curso TYPES.REF_CURSOR; 

BEGIN 

OPEN CURSOR_curso FOR 

SELECT Curs_id_PK, Curs_codi, Curs_nomb, Curs_cred, Curs_hora_sema, Curs_anno, Cicl_id_FK, Carr_id_FK FROM TB_Curso WHERE Curs_id_PK = P_Curs_id_PK; 

RETURN CURSOR_curso;

END;

/

PROMPT INSERT_CURSO_X_ESTUDIANTE

create or replace PROCEDURE insert_curso_x_estudiante(Curs_id_FK IN TB_Curso_X_Estudiante.Curs_id_FK%TYPE, Estu_id_FK IN TB_Curso_X_Estudiante.Estu_id_FK%TYPE)

AS

BEGIN

INSERT INTO TB_Curso_X_Estudiante VALUES(Curs_id_FK, Estu_id_FK);

END;

/

PROMPT DELETE_CURSO_X_ESTUDIANTE

create or replace PROCEDURE delete_curso_x_estudiante(P_Curs_id_FK IN TB_Curso_X_Estudiante.Curs_id_FK%TYPE, P_Estu_id_FK IN TB_Curso_X_Estudiante.Estu_id_FK%TYPE)

AS

BEGIN

DELETE FROM TB_Curso_X_Estudiante WHERE Curs_id_FK = P_Curs_id_FK AND Estu_id_FK = P_Estu_id_FK;

END;

/

PROMPT QUERY_CURSO_X_ESTUDIANTE

CREATE OR REPLACE FUNCTION query_curso_x_estudiante(P_Estu_id_FK IN TB_Curso_X_Estudiante.Estu_id_FK%TYPE)

RETURN TYPES.REF_CURSOR 

AS 

CURSOR_curso_x_estudiante TYPES.REF_CURSOR; 

BEGIN 

OPEN CURSOR_curso_x_estudiante FOR 

SELECT Curs_id_FK, Estu_id_FK FROM TB_Curso_X_Estudiante WHERE Estu_id_FK = P_Estu_id_FK;

RETURN CURSOR_curso_x_estudiante;

END;

/

--------------------------------------------------------------------------------------------------------------------
--Bucar
PROMPT QUERY_CURSO_CODIGO

CREATE OR REPLACE FUNCTION query_curso_codigo(P_Curs_id_PK IN TB_Curso.Curs_codi%TYPE)
RETURN TYPES.ref_cursor

AS

CURSOR_CURSO TYPES.ref_cursor;

BEGIN

OPEN  CURSOR_CURSO  FOR

SELECT Curs_id_PK, Curs_codi, Curs_nomb, Curs_cred, Curs_hora_sema, Curs_anno, Cicl_id_FK, Carr_id_FK FROM TB_Curso WHERE Curs_codi = P_Curs_id_PK; 

RETURN CURSOR_CURSO;

END;
/
--------------------------------------------------------------------------------------------------------------------
--Bucar
PROMPT QUERY_CURSO_NOMBRE

CREATE OR REPLACE FUNCTION query_curso_nombre(P_Curs_nomb IN TB_Curso.Curs_nomb%TYPE)

RETURN TYPES.ref_cursor

AS

CURSOR_CURSO TYPES.ref_cursor;

BEGIN

OPEN  CURSOR_CURSO  FOR

SELECT Curs_id_PK, Curs_codi, Curs_nomb, Curs_cred, Curs_hora_sema, Curs_anno, Cicl_id_FK, Carr_id_FK FROM TB_Curso WHERE Curs_nomb = P_Curs_nomb; 

RETURN CURSOR_CURSO;

END;
/
--------------------------------------------------------------------------------------------------------------------
--Bucar
PROMPT QUERY_CURSO_CARRERA

CREATE OR REPLACE FUNCTION query_curso_carrera(P_Carr_id_FK IN TB_Curso.Carr_id_FK%TYPE)

RETURN TYPES.ref_cursor

AS

CURSOR_CURSO TYPES.ref_cursor;

BEGIN

OPEN  CURSOR_CURSO  FOR

SELECT Curs_id_PK, Curs_codi, Curs_nomb, Curs_cred, Curs_hora_sema, Curs_anno, Cicl_id_FK, Carr_id_FK FROM TB_Curso WHERE TB_Curso.Carr_id_FK = P_Carr_id_FK; 

RETURN CURSOR_CURSO;

END;
/

---------------------------------------------------------------------------------------------------------------------

PROMPT LIST_CURSO

CREATE OR REPLACE FUNCTION list_curso

RETURN TYPES.REF_CURSOR 

AS 

CURSOR_curso TYPES.REF_CURSOR; 

BEGIN 

OPEN CURSOR_curso FOR 

SELECT Curs_id_PK, Curs_codi, Curs_nomb, Curs_cred, Curs_hora_sema, Curs_anno, Cicl_id_FK, Carr_id_FK FROM TB_Curso; 

RETURN CURSOR_curso; 

END;

/

PROMPT DELETE_CURSO

create or replace PROCEDURE delete_curso(P_Curs_id_PK IN TB_Curso.Curs_id_PK%TYPE)

AS

BEGIN

DELETE FROM TB_Curso WHERE Curs_id_PK = P_Curs_id_PK;

IF SQL%NOTFOUND THEN
	RAISE_APPLICATION_ERROR (-20201,'No se encontró ningún curso para eliminar con este identificador.');
END IF;

END;

/
--------------------------------------------------------------------------------------------------------------------
--------------------------------------------------Procesos Carrera--------------------------------------------------
--Bucar
PROMPT QUERY_CARRERA

CREATE OR REPLACE FUNCTION query_carrera(P_Carr_id_PK IN TB_Carrera.Carr_id_PK%TYPE)

RETURN TYPES.ref_cursor

AS

CURSOR_CARRERA TYPES.ref_cursor;

BEGIN

OPEN  CURSOR_CARRERA  FOR

SELECT TB_Carrera.Carr_id_PK, TB_Carrera.Carr_codi, TB_Carrera.Carr_nomb, TB_Carrera.Carr_titu FROM TB_Carrera WHERE TB_Carrera.Carr_id_PK = P_Carr_id_PK;

RETURN CURSOR_CARRERA;

END;
/
--------------------------------------------------------------------------------------------------------------------
--Bucar
PROMPT QUERY_CARRERA

CREATE OR REPLACE FUNCTION query_carrera_codigo(P_Carr_codi IN TB_Carrera.Carr_codi%TYPE)

RETURN TYPES.REF_CURSOR 

AS

CURSOR_CARRERA TYPES.REF_CURSOR; 

BEGIN

OPEN  CURSOR_CARRERA  FOR

SELECT Carr_id_PK, Carr_codi, Carr_nomb, Carr_titu FROM TB_Carrera WHERE Carr_codi = P_Carr_codi;

RETURN CURSOR_CARRERA;

END;
/

--------------------------------------------------------------------------------------------------------------------
--Bucar
PROMPT QUERY_CARRERA

CREATE OR REPLACE FUNCTION query_carrera_nombre(P_Carr_nomb IN TB_Carrera.Carr_nomb%TYPE)

RETURN TYPES.ref_cursor

AS

CURSOR_CARRERA TYPES.ref_cursor;

BEGIN

OPEN  CURSOR_CARRERA  FOR

SELECT TB_Carrera.Carr_id_PK, TB_Carrera.Carr_codi, TB_Carrera.Carr_nomb, TB_Carrera.Carr_titu FROM TB_Carrera WHERE TB_Carrera.Carr_nomb = P_Carr_nomb;

RETURN CURSOR_CARRERA;

END;
/

---------------------------------------------------------------------------------------------------------------------
--Insertar
PROMPT INSERT_CARRERA
CREATE OR REPLACE PROCEDURE insert_carrera(Carr_codi IN TB_Carrera.Carr_codi%TYPE, Carr_nomb IN TB_Carrera.Carr_nomb%TYPE, Carr_titu IN TB_Carrera.Carr_titu%TYPE)

AS 

BEGIN 

INSERT INTO TB_Carrera VALUES(Carr_sec_id.nextval, Carr_codi, Carr_nomb, Carr_titu);

END;
/

---------------------------------------------------------------------------------------------------------------------
--Modificar
CREATE OR REPLACE PROCEDURE update_carrera(p_id IN TB_Carrera.Carr_id_PK%TYPE, p_codigo IN TB_Carrera.Carr_codi%TYPE,p_nombre IN TB_Carrera.Carr_nomb%TYPE,p_titulo IN TB_Carrera.Carr_titu%TYPE)

AS

BEGIN

UPDATE TB_Carrera SET Carr_codi = p_codigo, Carr_nomb = p_nombre, Carr_titu = p_titulo  WHERE Carr_id_PK = p_id;

END;

/
---------------------------------------------------------------------------------------------------------------------
--Eliminar
PROMPT DELETE_CARRERA
CREATE OR REPLACE PROCEDURE delete_carrera(P_Carr_id_PK IN TB_Carrera.Carr_id_PK%TYPE)

AS

BEGIN

DELETE FROM TB_Carrera WHERE Carr_id_PK = P_Carr_id_PK;

IF SQL%NOTFOUND THEN
	RAISE_APPLICATION_ERROR (-20201,'No se encontró ninguna carrera para eliminar con este identificador.');
END IF;

END;

/

---------------------------------------------------------------------------------------------------------------------
--Listar Carrera
PROMPT LIST_CARRERA
CREATE OR REPLACE FUNCTION list_carrera

RETURN TYPES.REF_CURSOR 

AS 

CURSOR_CARRERA TYPES.REF_CURSOR; 

BEGIN 

OPEN CURSOR_CARRERA FOR 

SELECT Carr_id_PK, Carr_codi, Carr_nomb, Carr_titu FROM TB_Carrera;

RETURN CURSOR_CARRERA; 

END;
/


--------------------------------------------------------------------------------------------------------------------
--------------------------------------------------Procesos Ciclo--------------------------------------------------
--Insertar
PROMPT INSERT_CICLO
CREATE OR REPLACE PROCEDURE insert_ciclo(Cicl_anno IN TB_Ciclo.Cicl_anno%TYPE,
Cicl_nume IN TB_Ciclo.Cicl_nume%TYPE, Cicl_fech_inic IN TB_Ciclo.Cicl_fech_inic%TYPE, Cicl_fech_fina IN TB_Ciclo.Cicl_fech_fina%TYPE)

AS 

BEGIN 

INSERT INTO TB_Ciclo VALUES(Cicl_sec_id.nextval, Cicl_anno, Cicl_nume, Cicl_fech_inic, Cicl_fech_fina);

END;
/

---------------------------------------------------------------------------------------------------------------------
--Modificar
CREATE OR REPLACE PROCEDURE update_ciclo(p_Cicl_id_PK IN TB_Ciclo.Cicl_id_PK%TYPE, p_Cicl_anno IN TB_Ciclo.Cicl_anno%TYPE,
p_Cicl_nume IN TB_Ciclo.Cicl_nume%TYPE, p_Cicl_fech_inic IN TB_Ciclo.Cicl_fech_inic%TYPE, p_Cicl_fech_fina IN TB_Ciclo.Cicl_fech_fina%TYPE)

AS

BEGIN

UPDATE TB_Ciclo SET Cicl_anno = p_Cicl_anno, Cicl_nume = p_Cicl_nume, Cicl_fech_inic = p_Cicl_fech_inic, Cicl_fech_fina = p_Cicl_fech_fina  WHERE Cicl_id_PK = p_Cicl_id_PK;

END;

/

---------------------------------------------------------------------------------------------------------------------
--Buscar
PROMPT QUERY_CICLO

CREATE OR REPLACE FUNCTION query_ciclo(P_Cicl_id_PK IN TB_Ciclo.Cicl_id_PK%TYPE)

RETURN TYPES.ref_cursor

AS

CURSOR_CICLO TYPES.ref_cursor;

BEGIN

OPEN  CURSOR_CICLO  FOR

SELECT Cicl_id_PK, Cicl_anno, Cicl_nume, Cicl_fech_inic, Cicl_fech_fina FROM TB_Ciclo WHERE Cicl_id_PK = P_Cicl_id_PK;

RETURN CURSOR_CICLO;

END;
/

---------------------------------------------------------------------------------------------------------------------
--Listar Ciclera
PROMPT LIST_CICLO
CREATE OR REPLACE FUNCTION list_ciclo

RETURN TYPES.REF_CURSOR 

AS 

CURSOR_CICLO TYPES.REF_CURSOR; 

BEGIN 

OPEN CURSOR_CICLO FOR 

SELECT Cicl_id_PK, Cicl_anno, Cicl_nume, Cicl_fech_inic, Cicl_fech_fina FROM TB_Ciclo;

RETURN CURSOR_CICLO; 

END;

/

---------------------------------------------------------------------------------------------------------------------
--Eliminar
PROMPT DELETE_CICLO
CREATE OR REPLACE PROCEDURE delete_ciclo(P_Cicl_id_PK IN TB_Ciclo.Cicl_id_PK%TYPE)

AS

BEGIN

DELETE FROM TB_Ciclo WHERE Cicl_id_PK = P_Cicl_id_PK;

IF SQL%NOTFOUND THEN
	RAISE_APPLICATION_ERROR (-20201,'No se encontró ningún ciclo para eliminar con este identificador.');
END IF;

END;

/

---------------------------------------------------------------------------------------------------------------------
PROMPT #1 inserts Carreras
call insert_carrera('A101', 'Informatica', 'Diplomado');
call insert_carrera('A102', 'Matematica', 'Diplomado');
call insert_carrera('A103', 'Agronomia', 'Bachillerato');
call insert_carrera('A104', 'Topografia', 'Bachillerato');
call insert_carrera('A101', 'Informatica', 'Diplomado');
select * from TB_Carrera;

PROMPT #1 inserts Ciclos
call insert_ciclo(2020, 1, to_date('12-02-2020','DD-MM-YYYY'), to_date('12-07-2020','DD-MM-YYYY'));
call insert_ciclo(2020, 2, to_date('12-07-2020','DD-MM-YYYY'), to_date('12-11-2020','DD-MM-YYYY'));
select * from TB_Ciclo;

PROMPT #1 inserts Cursos
call insert_curso('B101', 'Programacion I', 4, 8, 1, 1, 1);
call insert_curso('B102', 'Programacion II', 4, 8, 1, 2, 2);
call insert_curso('B103', 'Programacion III', 4, 8, 2, 1, 1);
call insert_curso('B103', 'a', 4, 8, 2, 1, 1);
call insert_curso('B103', 'a', 4, 8, 2, 1, 1);
select * from TB_Curso;

PROMPT #1 inserts Profesor
call insert_profesor('604','Jose','B','C','8396','jo13');
select * from TB_Profesor;

PROMPT #1 inserts Estudiante
call insert_estudiante('Alexis','B','C',19);
select * from TB_Estudiante;

PROMPT #1 inserts Usuarios
call insert_usuario('Jo1234','1234','P',1);
call insert_usuario('Ale1234','1234','A',1);
call insert_usuario('1','1','A',1);
select * from TB_Usuario;

commit;
