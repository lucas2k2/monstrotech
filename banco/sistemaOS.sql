/**
* Sistema para gestão de OS 
* @Autor Lucas Silva
*/

create database dbsistema;
 use dbsistema; 
 show tables;
 
 
 create table usuarios (
     id int primary key auto_increment,
     nome varchar(50) not null,
     -- unique nao permite valores duplicados no campo
     login varchar(15) not null unique,
     senha varchar(250) not null,
     perfil varchar (10) not null
     );
	
     
     
describe usuarios;
 
 drop table usuarios;
 
-- descrever a tabela 
describe usuarios;

-- uso do md5 () para criptografar uma senha 
insert into usuarios(nome,login,senha,perfil) values('Administrador','admin', md5('admin'), 'admin');
insert into usuarios(nome,login,senha,perfil) values('lucas muller','muller', md5('muller'), 'muller');

select * from usuarios order by nome;
select * from usuarios order by nome desc;

-- selecionar tudo da tabela 
select * from usuarios;

-- uso do md5() para criptografar uma senha
select * from usuarios;

-- CRUD CREATE-----------------------------------------
insert into Usuarios (id,nome,login,senha) 
values('1','Lucas Silva','muller', ('123@senac'));

-- busca avançada pelo nome (estilo google)
select * from usuarios where nome like 'r%' order by nome; 



-- pesquisa avançada 
select nome from Usuarios;
select nome.login from Usuarios;
select * from usuarios where nome like 'l%';
select * from usuarios where nome = "Lucas Silva";
select * from usuarios where id =1;


-- login(autenticação)

select * from Usuarios where nome = 'admin' and senha = md5 ('admin');

update Usuarios set nome = 'willian bonner', login = 'willian', senha = 'w123@senac' where id =2;



create database dbsistema;
 use dbsistema;
 show tables;



create table Clientesd (
	 idCli int primary key auto_increment,
     nome varchar(40) not null,
     telefone varchar(12) not null ,
     cpf varchar(11) not null unique,
     endereco varchar (50) not null,
     numero varchar (5) not null,
     cep varchar (9) not null,
     bairro varchar (30) not null,
     complemento varchar (20) not null,
     cidade varchar (30) not null,
     uf varchar (2) not null
     );
     
     describe Clientesd;
 
 drop table Clientesd;
 
-- descrever a tabela 
describe Clientesd;

-- selecionar tudo da tabela 
select * from Clientesd;

-- uso do md5() para criptografar uma senha

-- CRUD CREATE-----------------------------------------
insert into Clientesd (nome,telefone,cpf, endereco, numero,cep,complemento,bairro,cidade,uf) 
values('Lucas Silva', '985148502', '51603205802' ,'av lins de vasconselos',  '336', '02521000', 'petshop', 'casa verde', 'sao paulo', 'sp');



-- pesquisa avançada 
select nome from Clientesd;
select nome.telefone from Clientesd;
select * from Clientesd where nome like 'l%';
select * from Clientesd where nome = null;
select * from Clientesd where telefone =null;
select * from Clientesd where cpf =null;
select * from Clientesd where endereco  =null;
select * from Clientesd where numero  =null;


update Clientesd set nome = 'Luucas', telefone = '985148502', cpf = '51603205802', endereco = 'av lins de vasconselos', numero = '336', cep = '02521000', bairro = 'casa verde media', complemento = 'sim', cidade = 'Sao paulo', UF = 'sp' where idCli=?;
     
    -- CRUD Delete --------------------
-- CUIDADO!!!! (Sempre usar a clausula where junto a chave primaria) 

delete from Clientesd where idCli = 7; 


/* Relacionamento de tabelas 1 - N*/
-- timestamp default current_timestamp (data e hora automatica)
-- decimal (numeros nao inteiros) 10,2 (digitos, casas decimais)
-- foreign key (FK) chave estrangeira 
-- 1 (FK) ----------- N (PK)

create table servicos (
    os int primary key auto_increment,
    DataOS timestamp default current_timestamp,
    Modelo varchar (200) not null,
    Defeito varchar (200) not null,
    Valor decimal (10,2),
    id int not null unique,
    foreign key (id) references Clientesd (idCli)
    );
    
     select * from servicos;
     
     drop table servicos;
    
    insert into servicos (Modelo,Defeito,Valor,idCli) values ('Notbook LeNovo 690', 'Troca da fonte', 250,1);
    
    -- selecionando o conteudo de 2 ou mais tabelas
    
    select * from servicos
   inner join cliente
   on servicos.idcli - cliente.idcli;
    
    delete from cliente where idcli =1;
    
    drop table servicos;
    
    /** RELATORIOS **/
    select nome,fone from Clientesd order by nome;
    
    SELECT * FROM Clientesd;
 
 -- servicos
 select 
 servicos.os,servicos.DataOS,servicos.Modelo,servicos.Defeito,servicos.Valor,
 Clientesd.nome
 from servicos
 inner join Clientesd
 on servicos.id = Clientesd.idCli;


     