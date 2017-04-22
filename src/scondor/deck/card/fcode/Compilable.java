package scondor.deck.card.fcode;

public interface Compilable<DATA extends CompileData> {
	
	public void compile(Vars vars, String cmd, DATA data);
	
}
