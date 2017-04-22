package scondor.event;

import scondor.deck.card.Card;
import scondor.deck.card.fcode.CompileData;
import scondor.deck.card.fcode.TargetType;
import scondor.deck.card.troops.ATCard;
import scondor.deck.card.troops.DTCard;
import scondor.event.cardevent.CardEvent;
import scondor.event.spawnevent.SpawnATEvent;
import scondor.event.spawnevent.SpawnCompileData;
import scondor.event.spawnevent.SpawnDTEvent;
import scondor.event.spawnevent.SpawnTroopEvent;
import scondor.session.SessionController;

public class EventMaster {
	
	private static Events<CompileData, CardEvent> cardevents = new Events<>();
	private static Events<SpawnCompileData<? extends Card<?>>, SpawnTroopEvent<SpawnCompileData<? extends Card<?>>>> spawntroopevents = new Events<>();
	private static Events<SpawnCompileData<ATCard>, SpawnATEvent> spawnatcardevents = new Events<>();
	private static Events<SpawnCompileData<DTCard>, SpawnDTEvent> spawndtcardevents = new Events<>();
	
	public static void triggerCardEvents(SessionController controller) {
		cardevents.trigger(new CompileData(controller, TargetType.NONE));
	}
	
	public static void triggerSpawnATEvents(SessionController controller, ATCard card) {
		SpawnCompileData<ATCard> compiler = new SpawnCompileData<ATCard>(controller, card);
		spawntroopevents.trigger(compiler);
		spawnatcardevents.trigger(compiler);
	}
	
	public static void triggerSpawnDTEvents(SessionController controller, DTCard card) {
		SpawnCompileData<DTCard> compiler = new SpawnCompileData<DTCard>(controller, card);
		spawntroopevents.trigger(compiler);
		spawndtcardevents.trigger(compiler);
	}
	
	public static void addCardEvent(CardEvent event) {
		EventMaster.cardevents.addEvent(event);
	}
	
}
