import java.util.HashMap;
import java.util.Map;

public class Turma {

    final private Disciplina disciplina;
    private Professor professor;
    final private Periodo periodo;

    final private Map<Long, Aluno> alunoByDre;
    final private Map<Long, Float> notaByDre;

    public Turma(Disciplina disciplina, Professor professor, Periodo periodo){

        this.professor = professor;
        this.disciplina = disciplina;
        this.alunoByDre = new HashMap<>();
        this.periodo = Siguinha.obterPeriodoCorrente();
        this.notaByDre = new HashMap<>();
    }

    public void inscreverAluno(Aluno aluno)
    {
        long dre = aluno.getDre();
        Aluno alunoJaInscrito = alunoByDre.get(dre);
        if (alunoJaInscrito != null) {
            // nada a fazer, pois o aluno já está inscrito
            return;
        }
        alunoByDre.put(dre, aluno);
    }

    public void atribuirMediaFinal(long dre, float nota)
    {
        //para adicionar a nota o aluno deve estar inscrito na matéria
        Aluno alunoJaInscrito = alunoByDre.get(dre);
        if (alunoJaInscrito != null) {
            notaByDre.put(dre, nota);
        }
    }

    public float obterMediaFinal(long dre)
    {
        return notaByDre.get(dre);
    }

    public String listarAlunos(){
        int tamanho = alunoByDre.size();
        int contador = 1;
        int tamanhoDaString = 0;
        for (Aluno aluno : alunoByDre.values()) {
            int size = aluno.getNome().length();
            tamanhoDaString += size;
        }
        StringBuilder output = new StringBuilder(tamanhoDaString);
        for (Aluno aluno : alunoByDre.values()) {
            output.append(aluno.getNome());
            if(contador < tamanho){
                output.append(',');
                contador ++;
            }
            else{
                output.append('.');
            }
        }
        return output.toString();
    }

}
