package com.example.witchbornroster;

import java.util.ArrayList;

public class WarriorFactory extends Warrior {
    public static final int WARLORD_VALUE = 12;
    public static final int HARBINGER_VALUE = 6;
    public static final int FREEBOOTER_VALUE = 9;
    public static final int SCROUGE_VALUE = 14;
    public static final int REAVER_VALUE = 15;
    public static final int OGRE_VALUE = 16;
    public static final int JARL_VALUE = 12;
    public static final int BINDER_VALUE = 6;
    public static final int KARL_VALUE = 9;
    public static final int BOLT_HUNTER_VALUE = 11;
    public static final int AX_OF_JARL_VALUE = 15;
    public static final int DRAGON_HUNTER_VALUE = 15;

    public static Warrior createWarrior(String type){
        Warrior warrior = new Warrior();
        ArrayList<SkillContainer> skills = new ArrayList<SkillContainer>();

        if(type == "Warlord"){
            warrior.setName("Warlord");
            warrior.setType("Warlord");
            warrior.setTypeValue(WARLORD_VALUE);
            warrior.setBaseDefense(5);
            warrior.setStrength(3);
            warrior.setAgility(3);
            warrior.setPsyche(2);
            warrior.setSpeed(6);
            warrior.setRoutBonus(2);


            skills.add(new SkillContainer(animosity, 0));
            skills.add(new SkillContainer(feral, 0));
            skills.add(new SkillContainer(command, 0));
            skills.add(new SkillContainer(new Skill("Orc", Skill.MASTER_SKILL, "Shield Rush", "Heavy Shield", "Developed to ravage ranks of Elven archers, Shield Rush imparts protection plus the crushing impact of a bludgeon.\n PROTECTION. Block the attack when an opponent rolls a shield hit. Be protected against any number of shield hits in the round.\\n SHIELD ATTACK FIRST. Get attack dice like any melee weapon. Use damage and injury modifiers listed for a heavy shield in Melee Weapons. Batter, Clobber, or a shield spike improve these numbers. Roll an injury outcome if needed but the result can‘t wound an opponent; he’s stunned at worst. Never take more than 1 shield attack per turn.\n MELEE WEAPON SECOND. Finish with your warrior’s normal melee attack(s).\n CHARGE! Attack once with the shield and once with a melee weapon.\n", "Shield Rush prohibits using 2-handed weapons or skills. Opponents with Zero In can ignore your warrior’s heavy shield on missile shots. Sidestep allows only one shield or weapon attack. Doesn’t combine with Blade Barrier, Blades, Blades Master, Bull Rush, Gird, Nari, Onslaught, Shield, or Shield Bash.\n Riders: Can’t use shield attack while mounted.\n Fliers: Gain normal protection in the air. Since your flier only attacks once per turn, choose between the heavy shield or other weapon."), 0));
        }
        else if(type == "Harbinger"){
            warrior.setName("Harbinger");
            warrior.setType("Harbinger");
            warrior.setTypeValue(HARBINGER_VALUE);
            warrior.setBaseDefense(5);
            warrior.setStrength(3);
            warrior.setAgility(3);
            warrior.setPsyche(2);
            warrior.setSpeed(6);
            warrior.setRoutBonus(2);

            skills.add(new SkillContainer(animosity, 0));
            skills.add(new SkillContainer(feral, 0));
            skills.add(new SkillContainer(new Skill("Psyche", Skill.BASIC_SKILL, "Rally", "Battle Standard", "Inspire a comrade shaken by the sight of friends being slaughtered or one who falters when asked to charge a marauding troll.\nINTERRUPT WHEN A COMRADE FAILS A ROUT FEAT. Remove 1 shaken condition marker from a comrade within short range.\nYour standard bearer can’t have a condition marker to interrupt. He’s marked with a chit afterward or a strike last condition marker if already marked.\nUNNERVING CREATURES. Also interrupt to Rally a comrade who fails a rout feat going after a creature with Fearsome, Fear, or Terror. Your comrade is free to approach or attack the creature. Mark your standard bearer with a chit or condition marker.", "Each standard bearer can only use Rally 1 time per round."), 0));
        }
        else if(type == "Freebooter"){
            warrior.setName("Freebooter");
            warrior.setType("Freebooter");
            warrior.setTypeValue(FREEBOOTER_VALUE);
            warrior.setBaseDefense(5);
            warrior.setStrength(3);
            warrior.setAgility(3);
            warrior.setPsyche(2);
            warrior.setSpeed(6);
            warrior.setRoutBonus(2);

            skills.add(new SkillContainer(animosity, 0));
            skills.add(new SkillContainer(feral, 0));
            skills.add(new SkillContainer(new Skill("Speed", Skill.BASIC_SKILL, "Withdraw", "", "Nicknamed “the reverse charge,” this quick and sometimes wild attack distracts an opponent. While he’s reeling, your warrior retreats.\\nDECLARE “WITHDRAW” BEFORE A MELEE ATTACK. Resolve the attack first. Whether it hits or not, your warrior automatically evades without being contested and moves away up to the limit of his Speed. Any attacks beyond the first are forfeited.\\nOOPS. The turn ends before your warrior can Withdraw.", "An opponent with Engage can contest the evade attempt. Your warrior must roll an Agility feat but isn’t penalized. Berserkers can’t earn Withdraw."), 0));
        }
        else if(type == "Scourge"){
            warrior.setName("Scourge");
            warrior.setType("Scourge");
            warrior.setTypeValue(SCROUGE_VALUE);
            warrior.setBaseDefense(5);
            warrior.setStrength(3);
            warrior.setAgility(3);
            warrior.setPsyche(2);
            warrior.setSpeed(6);
            warrior.setRoutBonus(2);

            skills.add(new SkillContainer(animosity, 0));
            skills.add(new SkillContainer(feral, 0));
            skills.add(new SkillContainer(new Skill("Strength", Skill.EXPERT_SKILL, "Brawler", "", "", ""),0));
            skills.add(new SkillContainer(new Skill("Defense", Skill.EXPERT_SKILL, "Disarm", "", "", ""),0));
            skills.add(new SkillContainer(new Skill("Orc", Skill.MASTER_SKILL, "Tireless", "", "", ""),0));
        }
        else if(type == "Reaver"){
            warrior.setName("Reaver");
            warrior.setType("Reaver");
            warrior.setTypeValue(REAVER_VALUE);
            warrior.setBaseDefense(5);
            warrior.setStrength(3);
            warrior.setAgility(2);
            warrior.setPsyche(1);
            warrior.setSpeed(5);
            warrior.setRoutBonus(2);
            warrior.setChargeBonus(2);

            skills.add(new SkillContainer(animosity, 0));
            skills.add(new SkillContainer(feral, 0));
            skills.add(new SkillContainer(fearless,0));
            skills.add(new SkillContainer(new Skill("Strength", Skill.EXPERT_SKILL, "Fury", "", "", ""),0));
            skills.add(new SkillContainer(new Skill("Orc", Skill.BASIC_SKILL, "Gore", "", "", ""),0));
            skills.add(new SkillContainer(new Skill("Orc", Skill.BASIC_SKILL, "Pounce", "", "", ""),0));
        }
        else if(type == "Ogre") {
            warrior.setName("Ogre");
            warrior.setType("Ogre");
            warrior.setTypeValue(OGRE_VALUE);
            warrior.setBaseDefense(5);
            warrior.setStrength(5);
            warrior.setAgility(2);
            warrior.setPsyche(1);
            warrior.setSpeed(6);
            warrior.setRoutBonus(2);

            skills.add(new SkillContainer(bigUn, 0));
            skills.add(new SkillContainer(rancour, 0));
            skills.add(new SkillContainer(feral, 0));
            skills.add(new SkillContainer(fearsome, 0));
            skills.add(new SkillContainer(stout, 0));
            skills.add(new SkillContainer(survival, 0));
        }
        else if(type == "Jarl"){
            warrior.setName("Jarl");
            warrior.setType("Jarl");
            warrior.setTypeValue(JARL_VALUE);
            warrior.setBaseDefense(5);
            warrior.setStrength(3);
            warrior.setAgility(2);
            warrior.setPsyche(3);
            warrior.setSpeed(6);

            skills.add(new SkillContainer(command, 0));
            skills.add(new SkillContainer(resistant, 0));
            skills.add(new SkillContainer(shield, 0));
            skills.add(new SkillContainer(stout, 0));
            skills.add(new SkillContainer(new Skill("Dwarf", Skill.BASIC_SKILL, "Unfettered", "", "", ""), 0));
        }
        else if(type == "Binder"){
            warrior.setName("Binder");
            warrior.setType("Binder");
            warrior.setTypeValue(BINDER_VALUE);
            warrior.setBaseDefense(4);
            warrior.setStrength(3);
            warrior.setAgility(2);
            warrior.setPsyche(2);
            warrior.setSpeed(5);

            skills.add(new SkillContainer(resistant, 0));
            skills.add(new SkillContainer(stout, 0));
            skills.add(new SkillContainer(new Skill("Psyche", Skill.BASIC_SKILL, "Bind Wounds", "", "", ""), 0));
        }
        else if(type == "Karl"){
            warrior.setName("Karl");
            warrior.setType("Karl");
            warrior.setTypeValue(KARL_VALUE);
            warrior.setBaseDefense(5);
            warrior.setStrength(3);
            warrior.setAgility(2);
            warrior.setPsyche(2);
            warrior.setSpeed(5);

            skills.add(new SkillContainer(resistant, 0));
            skills.add(new SkillContainer(shield, 0));
            skills.add(new SkillContainer(stout, 0));
            skills.add(new SkillContainer(new Skill("Agility", Skill.BASIC_SKILL, "Engage", "", "", ""), 0));
        }
        else if(type == "Bolt Hunter"){
            warrior.setName("Bolt Hunter");
            warrior.setType("Bolt Hunter");
            warrior.setTypeValue(BOLT_HUNTER_VALUE);
            warrior.setBaseDefense(5);
            warrior.setStrength(3);
            warrior.setAgility(3);
            warrior.setPsyche(2);
            warrior.setSpeed(6);

            skills.add(new SkillContainer(resistant, 0));
            skills.add(new SkillContainer(stout, 0));
        }
        else if(type == "Ax of the Jarl"){
            warrior.setName("Ax of the Jarl");
            warrior.setType("Ax of the Jarl");
            warrior.setTypeValue(AX_OF_JARL_VALUE);
            warrior.setBaseDefense(5);
            warrior.setStrength(3);
            warrior.setAgility(2);
            warrior.setPsyche(2);
            warrior.setSpeed(6);

            skills.add(new SkillContainer(resistant, 0));
            skills.add(new SkillContainer(shield, 0));
            skills.add(new SkillContainer(stout, 0));
            skills.add(new SkillContainer(fearless,0));
            skills.add(new SkillContainer(new Skill("Strength", Skill.EXPERT_SKILL, "Bull Rush", "", "", ""), 0));
            skills.add(new SkillContainer(new Skill("Strength", Skill.EXPERT_SKILL, "Fury", "", "", ""), 0));
        }
        else if(type == "Dragon Hunter"){
            warrior.setName("Dragon Hunter");
            warrior.setType("Dragon Hunter");
            warrior.setTypeValue(DRAGON_HUNTER_VALUE);
            warrior.setBaseDefense(5);
            warrior.setStrength(3);
            warrior.setAgility(3);
            warrior.setPsyche(3);
            warrior.setSpeed(6);

            skills.add(new SkillContainer(resistant, 0));
            skills.add(new SkillContainer(shield, 0));
            skills.add(new SkillContainer(stout, 0));
            skills.add(new SkillContainer(new Skill("Dwarf", Skill.BASIC_SKILL, "Grudgebearer", "", "", ""), 0));
            skills.add(new SkillContainer(new Skill("Dwarf", Skill.TRAIT, "Vigor", "", "", ""), 0));
        } else {
            return null;
        }

        warrior.setSkillList(skills);
        warrior.calcWarriorValue();
        return warrior;
    }


    static Skill animosity = new Skill("Traits", Skill.TRAIT, "Animosity", "", "Orcs are known for rape and pillage. Guild are assumed to be assassins and thugs. None fit comfortably within “society.” When approaching strangers this hostility plays out in negative ways.\\n REACTION CHECKS. Many discoveries feature a character who relays important information. A note at the top says, “A discoverer with Animosity or Rancor must make a reaction check.”\\n Before this discovery is read, roll D6 to determine how hostile the character feels toward your discoverer.\\n D6 Reaction Check\\n 1 The character attacks until he or your discoverer is wounded. No Shadow is awarded.\\n 2–3 The character runs off. No Shadow is awarded.\\n 4–6 The character is distrustful but the discovery plays out normally.", "No spell or skill erases Animosity.");
    static Skill feral = new Skill("Traits", Skill.TRAIT, "Feral", "", "Orcs are uncaring when a comrade falls in battle and numb to danger. This savage instinct adds +2 to rout feats.", "");
    static Skill command = new Skill("Traits", Skill.TRAIT, "Command", "Your leader can only use Command 1 time per round.\n", "Sometimes leadership is about bravery. Sometimes it’s about being a hero. But much of the time it’s about guidance and letting someone else enjoy the glory.\\nGIVE A COMRADE A RE-ROLL. Interrupt when your leader is within short range of the comrade and doesn’t have a condition marker.\\nRe-roll all the dice from the comrade’s last attack, feat, or injury outcome—even an oops. Goodbye, double skulls!\\nAfter the re-roll, mark your leader with a chit indicating his turn is done. If already marked, give him a strike last condition marker instead. Your leader can’t ever use Command on himself.\\nTHREE IN A ROW. Using Command is a turn AND still doesn’t let your war clan have more than 3 in a row. If a third warrior in a row needs Command, he’s out of luck.\\nA COMRADE WITH FIRST STRIKE CAN’T BENEFIT FROM COMMAND. Interrupts never work during the First Strike phase.\n", "");
    static Skill fearless = new Skill("Traits", Skill.TRAIT, "Fearless", "", "", "");
    static Skill bigUn = new Skill("Traits", Skill.TRAIT, "Big'Un", "" ,"", "");
    static Skill fearsome = new Skill("Traits", "Trait", "Fearsome", "", "", "");
    static Skill rancour = new Skill("Traits", "Trait", "Rancour", "", "", "");
    static Skill stout = new Skill("Traits", "Trait", "Stout", "", "", "");
    static Skill survival = new Skill("Psyche", Skill.BASIC_SKILL, "Stout", "", "", "");
    static Skill resistant = new Skill("Traits", "Trait", "Resistant", "", "", "");
    static Skill shield = new Skill("Defense", Skill.EXPERT_SKILL, "Shield", "", "", "");
}
