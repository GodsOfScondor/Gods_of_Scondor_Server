package scondor.event;

import scondor.deck.card.fcode.CompileData;
import scondor.deck.card.fcode.TargetType;
import scondor.event.cardevent.CardEvent;
import scondor.session.SessionController;

public class EventMaster {
	
	private static Events<CardEvent> cardevents = new Events<>();
	
	public static void triggerCardEffects(SessionController controller) {
		
		cardevents.trigger(new CompileData(controller, TargetType.CARDEFFECT));
		
	}
	
	public static void addCardEvent(CardEvent event) {
		EventMaster.cardevents.addEvent(event);
	}
	
}
