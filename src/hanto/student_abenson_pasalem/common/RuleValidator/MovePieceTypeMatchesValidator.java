/*******************************************************************************
 * This file was developed by Alec Benson and Peter Salem for CS4233: Object-Oriented Analysis & Design.
 * The course was taken at Worcester Polytechnic Institute.
 ******************************************/
package hanto.student_abenson_pasalem.common.RuleValidator;

import common.HantoCoordinate;
import common.HantoException;
import common.HantoPiece;
import common.HantoPieceType;

/**
 * Validates movement calls so that the pieceType matches the type on the board
 * @author Alec
 */
public class MovePieceTypeMatchesValidator implements IRuleValidator{
	@Override
	public void validate(IHantoRuleSet game, HantoPieceType pieceType, HantoCoordinate from, HantoCoordinate to)
			throws HantoException {
		HantoPiece piece = game.getBoard().getPieceAt(from);
		if(piece == null || (piece.getType() != pieceType)){
			throw new HantoException("The piece being moved does not match the piece type provided");
		}
	}
}
