package tillerino.tillerinobot.lang;

import java.util.List;
import java.util.Random;

import org.tillerino.osuApiModel.Mods;
import org.tillerino.osuApiModel.OsuApiUser;

import tillerino.tillerinobot.BeatmapMeta;
import tillerino.tillerinobot.IRCBot.IRCBotUser;
import tillerino.tillerinobot.RecommendationsManager.Recommendation;

/**
 * @author Jagemann http://osu.ppy.sh/u/3475234
 */
public class Default implements Language {

	@Override
	public String unknownBeatmap() {
		return "Jeg beklager, men jeg vet ikke om den banen. Det kan hende at den er veldig ny, veldig vanskelig, ikke verifisert eller ikke vanlig osu modus.";
	}

	@Override
	public String internalException(String marker) {
		return "Æsj da... Ser ut som at menneskelige Tillerino ødela ledningsnettet mitt."
				+ " Hvis han ikke ser det snart så kan du [https://github.com/Tillerino/Tillerinobot/wiki/Contact informere han]? (reference "
				+ marker + ")";
	}

	@Override
	public String externalException(String marker) {
		return "Hva er det som foregår? Jeg får bare rabalder fra osu serverne. Kan du fortelle meg hva dette er ment å bety? 0011101001010000"
				+ " Menneskelige Tillerino sier at det ikke er noe å bekymre seg over og at vi burde prøve igjen."
				+ " Hvis du er veldig bekymret av en eller annen grunn så kan du [https://github.com/Tillerino/Tillerinobot/wiki/Contact fortelle han]. (reference "
				+ marker + ")";
	}

	@Override
	public String noInformationForModsShort() {
		return "ikke noe data for forespurte modifikasjoner";
	}

	@Override
	public void welcomeUser(IRCBotUser user, OsuApiUser apiUser, long inactiveTime) {
		if (inactiveTime < 60 * 1000) {
			user.message("beep boop");
		} else if (inactiveTime < 24 * 60 * 60 * 1000) {
			user.message("Welcome back, " + apiUser.getUserName() + ".");
		} else if (inactiveTime > 7l * 24 * 60 * 60 * 1000) {
			user.message(apiUser.getUserName() + "...");
			user.message("...er det deg? Det har vært så lenge siden sist!");
			user.message("Det er godt å ha deg tilbake. Kan jeg interessere deg med en anbefalning?");
		} else {
			String[] messages = {
					"det ser ut som du har lyst på en anbefalning",
					"så fint å se deg! :)",
					"mitt favorittmenneske. (Ikke si noen andre mennesker at jeg sa det!)",
					"for en trivelig overraskelse! ^.^",
					"jeg håpet du skulle komme. Alle andre mennesker er så late, men ikke fortell dem at jeg sa det! :3",
					"hva har du lyst til å gjøre i dag?",
			};

			Random random = new Random();

			String message = messages[random.nextInt(messages.length)];

			user.message(apiUser.getUserName() + ", " + message);
		}
	}

	@Override
	public String unknownCommand(String command) {
		return "Unknown command \"" + command
				+ "\". Skriv !help hvis du trenger hjelp!";
	}

	@Override
	public String noInformationForMods() {
		return "Beklager, men jeg kan ikke gi deg informasjon om disse modifikasjonene akkurat nå.";
	}

	@Override
	public String malformattedMods(String mods) {
		return "Disse modifikasjonene ser ikke riktig ut. Modifikasjoner kan være enhver kombinasjon av DT HR HD HT EZ NC FL SO NF. Kombiner dem uten mellomrom og spesielle tegn. Eksempel: !with HDHR, !with DTEZ";
	}

	@Override
	public String noLastSongInfo() {
		return "Jeg kan ikke huske å ha gitt deg noen sang info...";
	}

	@Override
	public String tryWithMods() {
		return "Prøv denne banen med noen modifikasjoner!";
	}

	@Override
	public String tryWithMods(List<Mods> mods) {
		return "Prøv denne banen med " + Mods.toShortNamesContinuous(mods) + "!";
	}

	/**
	 * The user's IRC nick name could not be resolved to an osu user id. The
	 * message should suggest to contact @Tillerinobot or /u/Tillerino.
	 * 
	 * @param exceptionMarker
	 *            a marker to reference the created log entry. six or eight
	 *            characters.
	 * @param name
	 *            the irc nick which could not be resolved
	 * @return
	 */
	public String unresolvableName(String exceptionMarker, String name) {
		return "Navnet ditt forvirrer meg. Er du bannet? Hvis ikke så vær så snill [https://github.com/Tillerino/Tillerinobot/wiki/Contact contact Tillerino]. (reference "
				+ exceptionMarker + ")";
	}

	@Override
	public String excuseForError() {
		return "Unskyld meg, det var en praktfull kombinasjon av enere og nuller og jeg ble distrahert. Hva var det du ville sa du?";
	}

	@Override
	public String complaint() {
		return "Klagen din har blitt arkivert. Tillerino vil ta en titt på den når han kan.";
	}

	@Override
	public void hug(final IRCBotUser user, OsuApiUser apiUser) {
		user.message("Kom her du!");
		user.action("klemmer " + apiUser.getUserName());
	}

	@Override
	public String help() {
		return "Hei! Jeg er roboten som drepte Tillerino og tok over kontoen hans. Bare tuller, men ærlig talt så bruker jeg kontoen hans veldig mye."
				+ " [https://twitter.com/Tillerinobot status og oppdateringer]"
				+ " - [https://github.com/Tillerino/Tillerinobot/wiki kommandoer]"
				+ " - [http://ppaddict.tillerino.org/ ppaddict]"
				+ " - [https://github.com/Tillerino/Tillerinobot/wiki/Contact kontakt]";
	}

	@Override
	public String faq() {
		return "[https://github.com/Tillerino/Tillerinobot/wiki/FAQ Ofte stilte spørsmål]";
	}

	@Override
	public String featureRankRestricted(String feature, int minRank, OsuApiUser user) {
		return "Beklager, men akkurat nå så er " + feature + " bare tilgjengelig for spillere som har passert rank " + minRank + ".";
	}

	@Override
	public String mixedNomodAndMods() {
		return "Hva mener du ingen modifikasjoner med modifikasjoner?";
	}

	@Override
	public String outOfRecommendations() {
		return "Jeg har anbefalt deg alt jeg kan tenke på."
				+ " Prøv noen andre anbefalningsinstillinger eller bruk !reset. Hvis du ikke er sikker så sjekk ut !help.";
	}

	@Override
	public String notRanked() {
		return "Ser ut som at banen ikke er verifisert.";
	}

	@Override
	public void optionalCommentOnNP(IRCBotUser user,
			OsuApiUser apiUser, BeatmapMeta meta) {
		// regular Tillerino doesn't comment on this
	}

	@Override
	public void optionalCommentOnWith(IRCBotUser user, OsuApiUser apiUser,
			BeatmapMeta meta) {
		// regular Tillerino doesn't comment on this
	}

	@Override
	public void optionalCommentOnRecommendation(IRCBotUser user,
			OsuApiUser apiUser, Recommendation meta) {
		// regular Tillerino doesn't comment on this
	}

	@Override
	public boolean isChanged() {
		return false;
	}

	@Override
	public void setChanged(boolean changed) {

	}

	@Override
	public String invalidAccuracy(String acc) {
		return "Ugyldig treffsikkerhet: \"" + acc + "\"";
	}

	@Override
	public void optionalCommentOnLanguage(IRCBotUser user, OsuApiUser apiUser) {
		/*
		 * TRANSLATION NOTE: This line is sent to the user right after they have
		 * chosen this Language implementation. The English version refers to
		 * itself as the default version ("just the way I am"), so translating
		 * the English message doesn't make any sense.
		 * 
		 * Instead, we've been using the line
		 * "*Translator* helped me learn *Language*." in translations. Replace
		 * *Translator* with your osu name and *Language* with the name of the
		 * language that you are translating to, and translate the line into the
		 * new language. This serves two purposes: It shows that the language
		 * was changed and gives credit to the translator.
		 * 
		 * You don't need to use the line above, and you don't have have to give
		 * yourself credit, but you should show that the language has changed.
		 * For example, in the German translation, I just used the line
		 * "Nichts leichter als das!", which translates literally to
		 * "Nothing easier than that!", which refers to German being my first
		 * language.
		 * 
		 * Tillerino
		 * 
		 * P.S. you can put a link to your profile into the line like this:
		 * [https://osu.ppy.sh/u/2070907 Tillerino]
		 */
		user.message("Du har nå valgt Norsk som ditt språk. Det var [http://osu.ppy.sh/u/3475234 Jagemann] som hjalp meg med å lære dette.");
	}

	@Override
	public String invalidChoice(String invalid, String choices) {
		return "Sorry, men \"" + invalid
				+ "\"  beregnes ikke. Prøv disse: " + choices + "!";
	}

	@Override
	public String setFormat() {
		return "Syntaxen for å sette et parameter er !set (innstilling) (verdi). Prøv !help hvis du trenger flere pekepinner.";
	}
}
