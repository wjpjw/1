package checking.service;

import java.util.ArrayList;
import java.util.HashMap;

import modeling.model.State;
import modeling.model.Statechart;
import checking.model.Defect;
import checking.model.DefectType;
import checking.strategy.ExceptionStrategy;
import checking.strategy.InitExceptionStrategy;
import checking.strategy.UnreachableStrategy;

public class CheckingService {

	private static CheckingService service = new CheckingService();

	private ArrayList<Defect> defects = new ArrayList<>();

	private CheckingService() {

	}

	public ArrayList<Defect> getDefects() {
		return defects;
	}

	public ArrayList<Defect> searchDefect(State state) {
		ArrayList<Defect> results = new ArrayList<Defect>();
		for (int i = 0; i < defects.size(); i++) {
			if (state.equals(defects.get(i).getRelatedState()))
				results.add(defects.get(i));
		}
		return results;
	}

	public void setDefects(ArrayList<Defect> defects) {
		this.defects = defects;
	}

	public static CheckingService getInstance() {
		return service;
	}

	public ArrayList<Defect> check(Statechart stateChart) {
		defects.clear();
		if (stateChart == null)
			return null;

		ArrayList<State> iel = InitExceptionStrategy
				.returnExceptionStates(stateChart);
		saveDefects("initException", iel);

		ArrayList<State> ul = UnreachableStrategy
				.returnUnreachableStates(stateChart);
		saveDefects("unreachable", ul);

		ArrayList<State> el = ExceptionStrategy
				.returnExceptionStates(stateChart);
		saveDefects("exception", el);

		return defects;
	}

	public void saveDefects(String type, ArrayList<State> al) {
		if ("initException".equals(type)) {
			for (int i = 0; i < al.size(); i++) {
				Defect defect = new Defect();
				defect.setRelatedState(al.get(i));
				defect.setDescription("此状态既为初始点又为异常，对应函数规格可能存在错误！");
				defect.type = DefectType.init_from_exception;
				defects.add(defect);
			}
		} else if ("unreachable".equals(type)) {
			for (int i = 0; i < al.size(); i++) {

				String description = "此状态不可达，对应函数规格可能存在错误！";
				Defect defect = new Defect();
				defect.type = DefectType.unreacheable;
				defect.setRelatedState(al.get(i));
				defect.setDescription(description);
				defects.add(defect);

			}
		} else if ("exception".equals(type)) {
			for (int i = 0; i < al.size(); i++) {

				String description = "";
				Defect defect = new Defect();
				if (!al.get(i).isIs_exception()) {
					description = "此状态无法到达其他任何普通状态！";
					defect.type = DefectType.common_state_cannot_jmp_to_any_other_common_state;
				} else {
					description = "此异常状态能到达普通状态，对应函数规格可能存在错误！";
					defect.type = DefectType.exception_can_jmp_to_common_states;
				}
				defect.setRelatedState(al.get(i));
				defect.setDescription(description);
				defects.add(defect);
			}
		}
	}

}
