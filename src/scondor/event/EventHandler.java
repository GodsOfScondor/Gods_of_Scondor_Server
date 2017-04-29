package scondor.event;

import java.util.List;

import scondor.deck.card.Card;
import scondor.deck.card.fcode.CompileData;
import scondor.deck.card.fcode.TargetType;
import scondor.deck.card.troops.ATCard;
import scondor.deck.card.troops.DTCard;
import scondor.event.cardevent.ATCardEvent;
import scondor.event.cardevent.CardEvent;
import scondor.event.cardevent.DTCardEvent;
import scondor.event.spawnevent.SpawnATEvent;
import scondor.event.spawnevent.SpawnCompileData;
import scondor.event.spawnevent.SpawnDTEvent;
import scondor.event.spawnevent.SpawnTroopEvent;
import scondor.session.PlayerSide;
import scondor.session.SessionController;

public class EventHandler {
	
	private Events<CompileData, CardEvent<? extends Card<?>>> cardevents = new Events<>();
	private Events<CompileData, ATCardEvent> atcardevents = new Events<>();
	private Events<CompileData, DTCardEvent> dtcardevents = new Events<>();
	
	private Events<SpawnCompileData<? extends Card<?>>, SpawnTroopEvent<SpawnCompileData<? extends Card<?>>>> spawntroopevents = new Events<>();
	private Events<SpawnCompileData<ATCard>, SpawnATEvent> spawnatcardevents = new Events<>();
	private Events<SpawnCompileData<DTCard>, SpawnDTEvent> spawndtcardevents = new Events<>();
	
	/**
	 * 
	 * trigger all cardevents on a player side
	 * 
	 */
	public  void triggerCardEvents(SessionController controller) {
		
		/*
		 * get current player side
		 */
		PlayerSide onturn = controller.getSession().getPlayer();
		
		/*
		 * iterate attackers
		 */
		for (ATCard attacker : onturn.getAttackers()) if (attacker!=null) {
			
			/*
			 * trigger atcardevents 
			 */
			atcardevents.triggerEvent(new EventFilter<CompileData, ATCardEvent>() {
				@Override
				public ATCardEvent filter(List<ATCardEvent> events) {
					for (ATCardEvent event : events) if (event.getCard().getUUID()==attacker.getUUID()) return event;
					return null;
				}
			}, new CompileData(controller, TargetType.NONE));
			
			/*
			 * trigger cardevents 
			 */
			cardevents.triggerEvent(new EventFilter<CompileData, CardEvent<? extends Card<?>>>() {
				@Override
				public CardEvent<? extends Card<?>> filter(List<CardEvent<? extends Card<?>>> events) {
					for (CardEvent<? extends Card<?>> event : events) if (event.getCard()==attacker) return event;
					return null;
				}
			}, new CompileData(controller, TargetType.NONE));
			
		}
		
		/*
		 * iterate defenders
		 */
		for (DTCard defender : onturn.getDefenders()) if (defender!=null) {
			
			/*
			 * trigger dtcardevents 
			 */
			dtcardevents.triggerEvent(new EventFilter<CompileData, DTCardEvent>() {
				@Override
				public DTCardEvent filter(List<DTCardEvent> events) {
					for (DTCardEvent event : events) if (event.getCard()==defender) return event;
					return null;
				}
			}, new CompileData(controller, TargetType.NONE));
			
			/*
			 * trigger cardevents 
			 */
			cardevents.triggerEvent(new EventFilter<CompileData, CardEvent<? extends Card<?>>>() {
				@Override
				public CardEvent<? extends Card<?>> filter(List<CardEvent<? extends Card<?>>> events) {
					for (CardEvent<? extends Card<?>> event : events) if (event.getCard()==defender) return event;
					return null;
				}
			}, new CompileData(controller, TargetType.NONE));
			
		}
		
	}
	
	public void triggerSpawnATEvents(SessionController controller, ATCard card) {
		SpawnCompileData<ATCard> compiler = new SpawnCompileData<ATCard>(controller, card);
		spawntroopevents.trigger(compiler);
		spawnatcardevents.trigger(compiler);
	}
	
	public void triggerSpawnDTEvents(SessionController controller, DTCard card) {
		System.out.println("trigger all spawn events!");
		SpawnCompileData<DTCard> compiler = new SpawnCompileData<DTCard>(controller, card);
		spawntroopevents.trigger(compiler);
		spawndtcardevents.trigger(compiler);
	}
	
	public void addCardEvent(ATCard card, EventData data) {
		atcardevents.addEvent(new ATCardEvent(card, data));
	}
	
	public void addSpawnTroopEvent(SpawnTroopEvent<SpawnCompileData<? extends Card<?>>> event) {
		spawntroopevents.addEvent(event);
	}
	
}
