package sistema.bancario.src.models;

public class ContaBancariaBonus extends ContaBancaria {
	
	private int bonus;
	public ContaBancariaBonus() {
		this.bonus = 10;
	}
	public int getBonus() {
		return bonus;
	}
	public void setBonus(int bonus) {
		this.bonus = bonus;
	}
	

}
