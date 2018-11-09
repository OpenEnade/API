module openEnade

sig CodigoPostal, CodigoIES, CodigoCurso, CodigoArea, NomeCurso, NomeUniversidade, Ano {}

sig Regiao {
	estados : some Estado,
	universidades: set UniversidadePrivada
}

sig Estado{
	municipios: some Municipio,
	universidades: set UniversidadePublica
}

sig Municipio{
	campusUniversitarios : set Campus,
	cep : some CodigoPostal
}

sig Campus {
	cursos : some Curso
}
abstract sig Universidade{
	codigoIES: one CodigoIES,
	nome: one NomeUniversidade,
	campus: some Campus,
}
sig UniversidadePrivada, UniversidadePublica extends Universidade {}

abstract sig Curso {
	codCurso: one CodigoCurso,
	nome: one NomeCurso,
	codArea: one CodigoArea,
	notasEnade: one Enade
}

sig CursoPresencial, CursoEAD extends Curso {}

sig ConcluintesParticipantes, ConcluintesInscritos, NotaBrutaFG, NotaPadronizadaFG, NotaBrutaCE, NotaPadronizadaCE, EnadeContinuo, EnadeFaixa {}

sig Enade{
	notaBrutaFG: one NotaBrutaFG,
	notaPadronizadaFG: one NotaPadronizadaFG,
	notaBrutaCE: one NotaBrutaCE,
	notaPadronizadaCE: one NotaPadronizadaCE,
	enadeFaixa: one EnadeFaixa,
	enadeContinuo: one EnadeContinuo,
	inscritos: some ConcluintesInscritos,
	participantes: set ConcluintesParticipantes
}
fact {
	--Toda Universidade publica está relacionada com um estado
	all uPu:UniversidadePublica | one uPu.~universidades
	--Toda Universidade privada está relacionada com uma ou mais regiões
	all uPr:UniversidadePrivada| some uPr.~universidades
	--Todo Campus pertence a apenas um municipio
	all ca:Campus |  one ca.~campusUniversitarios
	--Todo CEP pertence a apenas um municipio
	all cp:CodigoPostal | one cp.~cep
	--Todo municipio pertence a apenas um estado
	all m:Municipio | one m.~municipios
	--Todo conjunto de notas do Enade está relacionado a um curso
	all en:Enade | one en.~notasEnade
	--Todo estado pertence a uma região
	all e:Estado | one e.~estados
	--Todo campus pertece a uma universidade
	all c:Campus | one c.~campus
	--Todo código IES pertence a uma universidade
	all codIes:CodigoIES | one codIes.~codigoIES
	--Todo curso pertence a uma universidade
	all cur:Curso | some cur.~cursos
	--Todo código do curso pertence a um Curso
	all codC:CodigoCurso | one codC.~codCurso
	--Para todos dois cursos, um curso é diferente do outro caso seus nomes e códigos sejam diferentes
	all c1, c2:Curso | (c1 != c2) => c1.nome != c2.nome and c1.codCurso != c2.codCurso
	--Para todas duas universidades, uma é diferente da outra caso seus nomes e códigos sejam diferentes
	all u1, u2:Universidade | (u1 != u2) => u1.nome != u2.nome and u1.codigoIES != u2.codigoIES
}

pred show{}
run show for 5

