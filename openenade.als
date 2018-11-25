module openEnade

sig Ano{}

sig CodigoPostal, CodigoIES, CodigoCurso, CodigoArea {}

sig NomeMunicipio, NomeCurso, NomeUniversidade, SiglaRegiao, SiglaEstado {}

sig Regiao {
	siglaRegiao: one SiglaRegiao
}

sig Estado{
	siglaEstado: one SiglaEstado,
	regiao: one Regiao
}

sig Municipio{
	nomeCidade: one NomeMunicipio,
	codigoPostal: one CodigoPostal,
	estado: one Estado
}

abstract sig Universidade{
	codigoIes: one CodigoIES,
	nomeUniversidade: one NomeUniversidade,
	campus: one Municipio,
	cursos: some Curso
}

sig UniversidadePrivada, UniversidadePublica extends Universidade {}

abstract sig Curso {
	codigoCurso: one CodigoCurso,
	codigoArea: one CodigoArea,
	nomeCurso: one NomeCurso	
}

sig CursoPresencial, CursoEAD extends Curso {}

sig ConcluintesParticipantes, ConcluintesInscritos, NotaBrutaFG, NotaPadronizadaFG, NotaBrutaCE, NotaPadronizadaCE, EnadeContinuo, EnadeFaixa {}

sig Nota{
	ano: one Ano,
	avaliacao: one Avaliacao,
	universidade: one Universidade
}

sig Avaliacao{
	curso:  one Curso,
	inscritos: some ConcluintesInscritos,
	participantes: set ConcluintesParticipantes,
	notaBrutaFG: one NotaBrutaFG,
	notaPadronizadaFG: one NotaPadronizadaFG,
	notaBrutaCE: one NotaBrutaCE,
	notaPadronizadaCE: one NotaPadronizadaCE,
	enadeContinuo: one EnadeContinuo,
	enadeFaixa: one EnadeFaixa	
}

fact Diferencas {
	--Para todos dois estados, um é diferente do outro se suas siglas forem diferntes
	all es1,es2:Estado | (es1 != es2) => es1.siglaEstado != es2.siglaEstado
	--Para todas duas regiões, uma é diferente da outra se suas siglas forem diferentes
	all reg1,reg2: Regiao | (reg1 != reg2) => reg1.siglaRegiao != reg2.siglaRegiao
	--Para todos dois cursos, um curso é diferente do outro caso seus nomes e códigos sejam diferentes
	all c1, c2:Curso | (c1 != c2) => c1.nomeCurso != c2.nomeCurso and c1.codigoCurso != c2.codigoCurso and c1.codigoArea != c2.codigoArea
	--Para todas duas universidades, uma é diferente da outra caso seus nomes, códigos e cursos sejam diferentes
	all u1, u2:Universidade | (u1 != u2) => u1.nomeUniversidade != u2.nomeUniversidade and u1.codigoIes != u2.codigoIes and u1.cursos != u2.cursos
	--Para todas duas cidades, uma é diferente da outra se seus nomes e codigos forem diferentes
	all cid1, cid2: Municipio | (cid1 != cid2) => cid1.nomeCidade != cid2.nomeCidade and cid1.codigoPostal != cid2.codigoPostal
	--Para todas duas notas, elas são diferentes se suas avaliações, anos e universidades forem diferentes
	all n1, n2: Nota | (n1 != n2) => n1.avaliacao != n2.avaliacao and n1.universidade != n2.universidade and n1.ano != n2.ano
	--Para todas duas avaliações, elas são diferentes se todos os seus atributos forem diferentes
	all av1, av2: Avaliacao | (av1 != av2) => av1.curso != av2.curso and av1.inscritos != av2.inscritos and av1.participantes != av2.participantes
	and av1.notaBrutaFG != av2.notaBrutaFG and av1.notaPadronizadaFG != av2.notaPadronizadaFG and av1.notaBrutaCE != av2.notaBrutaCE
	and av1.notaPadronizadaCE != av2.notaPadronizadaCE and av1.enadeContinuo != av2.enadeContinuo and av1.enadeFaixa != av2.enadeFaixa
	--Para toda avaliação, universidade e nota, os curso da avaliação e curso da universidade são iguais se estiverem contidos em Nota 
	all av: Avaliacao, u:Universidade, n:Nota| (av.curso != u.cursos) => av not in n.avaliacao or u not in n.universidade
}
--Garantia que todas as classes e atributos estejam ligadas a quem as possuem
fact {
	all u:Universidade | one u.~universidade
	all cp:CodigoPostal | one cp.~codigoPostal
	all codIes:CodigoIES | one codIes.~codigoIes
	all codC:CodigoCurso | one codC.~codigoCurso
	all av:Avaliacao | one av.~avaliacao
	all sigla: SiglaEstado | one sigla.~siglaEstado
	all sigla: SiglaRegiao | one sigla.~siglaRegiao
	all est:Estado | some est.~estado
	all reg: Regiao | some reg.~regiao
	all c:Curso | one c.~curso
	all i:ConcluintesInscritos | one i.~inscritos
	all p:ConcluintesParticipantes | one p.~participantes
	all bfg:NotaBrutaFG | one bfg.~notaBrutaFG
	all pfg:NotaPadronizadaFG | one pfg.~notaPadronizadaFG
	all bce:NotaBrutaCE | one bce.~notaBrutaCE
	all pce:NotaPadronizadaCE | one pce.~notaPadronizadaCE
	all ec:EnadeContinuo | one ec.~enadeContinuo
	all ef:EnadeFaixa | one ef.~enadeFaixa	
}

--Checa se todos os cursos que estão na avaliação da Nota pertecem aos cursos da Universidade avaliada
assert cursoInUniversidadeAndAvaliacao{
	all n:Nota, c:Curso| (c in n.avaliacao.curso) => c in n.universidade.cursos
}

check cursoInUniversidadeAndAvaliacao for 30
