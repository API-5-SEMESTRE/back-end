package api.theVelopers.sas.enumeration;

public enum SenioridadeVendedor {
	
	JUNIOR("Júnior"),
	PLENO("Pleno"),
	SENIOR("Senior");
	
	private final String desc;
	
	SenioridadeVendedor(final String desc) {
		this.desc = desc;
	}
	
	@Override
	public String toString() {
		return desc;
	}
}
