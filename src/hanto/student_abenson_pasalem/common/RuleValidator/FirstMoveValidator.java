package hanto.student_abenson_pasalem.common.RuleValidator;

import common.HantoCoordinate;
import common.HantoException;
import common.HantoPieceType;

public class FirstMoveValidator implements IRuleValidator{

	/**
	 * Ensures the first move is made at (0,0)
	 */
	public void validate(IHantoRuleSet game, HantoPieceType pieceType, HantoCoordinate from, HantoCoordinate to)
			throws HantoException {
		
		if (game.getRedTurns() == 0 && game.getBlueTurns() == 0) {
			if (to.getX() != 0 || to.getY() != 0) {
				throw new HantoException("The only valid space for the first move is (0,0)");
			}
		}
	}
	

}