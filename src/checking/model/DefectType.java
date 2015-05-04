package checking.model;

public enum DefectType {
	init_from_exception, unreacheable, exception_can_jmp_to_common_states, common_state_cannot_jmp_to_any_other_common_state;
	public static String id(DefectType type){
		switch (type) {
		case init_from_exception:
			return "D1";
		case unreacheable:
			return "D2";
		case exception_can_jmp_to_common_states:
			return "D3";
		case common_state_cannot_jmp_to_any_other_common_state:
			return "D4";
		default:
			return "NULL";
		}
	}
}
