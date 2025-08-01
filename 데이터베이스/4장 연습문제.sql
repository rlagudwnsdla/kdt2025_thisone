USE company_db;

select * from emp;
select * from dept;
--------------------------------------------------------------------------------------------------------------------
#(1)
select * from emp where mgr is null;


--------------------------------------------------------------------------------------------------------------------
#(2)
select ename, dname from emp e join dept d on e.DEPTNO = d.DEPTNO;
select ename, (SELECT dname FROM dept d WHERE e.DEPTNO = d.DEPTNO) as dname from emp e;


--------------------------------------------------------------------------------------------------------------------
#(3)
select ename from dept d join emp e on e.DEPTNO = d.DEPTNO where loc = 'CHICAGO';
select ename from emp where deptno in (select deptno from dept where loc = 'CHICAGO');
select ename from emp where deptno = (select deptno from dept where loc = 'CHICAGO');
select ename from emp e where exists(select * from dept d where d.DEPTNO = e.DEPTNO and loc = 'CHICAGO');


--------------------------------------------------------------------------------------------------------------------
#(4)
select ename from emp where sal > (select avg(SAL) from emp);


--------------------------------------------------------------------------------------------------------------------
#(5)
select e.ename from emp e join dept d on e.DEPTNO = d.DEPTNO
where d.dname = 'ACCOUNTING' and e.sal > (select avg(sal) from emp);

select ename from dept d join emp e on e.DEPTNO = d.DEPTNO
where d.dname = 'RESEARCH' and sal > (select avg(sal) from emp);

select ename from dept d join emp e on e.DEPTNO = d.DEPTNO
where d.dname = 'SALES' and sal > (select avg(sal) from emp);

select e.ename from emp e join dept d on e.DEPTNO = d.DEPTNO
where d.dname = 'OPERATIONS' and e.sal > (select avg(sal) from emp);

select e.ename from emp e join dept d on e.DEPTNO = d.DEPTNO
where DNAME in ('ACCOUNTING','RESEARCH','SALES','OPERATIONS') and sal > (select avg(sal) from emp);


--------------------------------------------------------------------------------------------------------------------
CREATE VIEW	SalesmanView AS	SELECT	e.empno, e.ename, e.sal, d.dname FROM Emp e, Dept d 
WHERE e.deptno=d.deptno AND job='SALESMAN';

select ename, sal from salesmanview limit 3;
			
            
            
CREATE VIEW	SalesTop AS	SELECT	empno, ename, sal, dname FROM SalesmanView
WHERE sal >= 1500;

select * from SalesTop;
