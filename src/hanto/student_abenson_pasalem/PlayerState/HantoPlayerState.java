
/*******************************************************************************
 * This file was developed by Alec Benson and Peter Salem for CS4233: Object-Oriented Analysis & Design.
 * The course was taken at Worcester Polytechnic Institute.
 ******************************************/
package hanto.student_abenson_pasalem.PlayerState;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import hanto.common.*;
import hanto.student_abenson_pasalem.common.HantoPieceBuilder;

/**
 * Manages state of players and keeps track of the player inventory, color, and whether the butterfly is in play
 */
public class HantoPlayerState {
	private final HantoPlayerColor color;
	private final Map<HantoPieceType, Integer> inventory;
	private boolean hasPlayedButterfly;
	
	/**
	 * Constructor for creating a new HantoPlayerState
	 * @param color
	 */
	public HantoPlayerState(HantoPlayerColor color){
		this.color = color;
		inventory = new HashMap<HantoPieceType, Integer>();
		inventory.put(HantoPieceType.BUTTERFLY, 1);
		hasPlayedButterfly = false;
	}
	
	/**
	 * Specify the number of a specific type of piece that the player has
	 * @param pieceType
	 * @param count
	 * @throws HantoException
	 */
	public void setStartPieceCount(HantoPieceType pieceType, int count) throws HantoException{
		if (inventory.get(pieceType) != null){
			throw new HantoException("The number of " + pieceType + " pieces can only be set once");
		}
		
		inventory.put(pieceType, count);
	}
	
	/**
	 * Plays a piece from the player's inventory
	 * @param pieceType
	 * @param pieceBuilder 
	 * @return the HantoPiece retrieved from the player's inventory
	 * @throws HantoException
	 */
	public HantoPiece getPieceFromInventory(HantoPieceType pieceType, HantoPieceBuilder pieceBuilder) throws HantoException {
		if(!inventory.containsKey(pieceType)){
			throw new HantoException(pieceType + " is not in use in this game");
		}
		int numLeft = inventory.get(pieceType);
		if(numLeft <= 0){
			throw new HantoException("Player " + color.name() + " "
					+ "has no more " + pieceType + " left to play. ");
		}
		inventory.put(pieceType, --numLeft);
		if(pieceType == HantoPieceType.BUTTERFLY){
			hasPlayedButterfly = true;
		}
		return pieceBuilder.getResult(color, pieceType);
	}
	
	/**
	 * Returns true if the player has played the butterfly or not
	 * @return
	 */
	public boolean getHasPlayedButterfly(){
		return hasPlayedButterfly;
	}
	
	/**
	 * Gets a list of all piece types left in the inventory
	 * @return
	 */
	public List<HantoPieceType> piecesInInventory(){
		return new ArrayList<HantoPieceType>(inventory.keySet());
	}
	
	/**
	 * Gets a count of all piece types left in the inventory
	 * @return
	 */
	public int numPiecesLeftInInventory(){
		return inventory.size();
	}
	
	/**
	 * Returns the player's color
	 */
	public HantoPlayerColor getColor(){
		return color;
	}
}
