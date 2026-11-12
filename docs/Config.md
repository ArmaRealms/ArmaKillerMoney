## Default config for ArmaKillerMoney 5.0.0

```yaml
Global-settings:
    Money:
        Decimal-places: 2
        Item-drop:
            Enabled: false
            Item-name: "&c${amount}"
            Item-material: GOLD_INGOT
            Anyone-can-pick-it-up: true
    Farming:
        Disable-spawner-farming: true
        Disable-spawner-egg-farming: false
    General:
        # CHAT, ACTION_BAR, or DISABLED
        Message-method: CHAT
        Allowed-gamemodes: SURVIVAL, CREATIVE
    Limit:
        Reset-time: 24
        Reached-limit-message: true
Configurable-permissions:
    Money-multipliers:
        donator: 1.5
        vip: 2
        elite: 2.5
    Limit-multipliers:
        donator: 1.5
        vip: 2
        elite: 2.5
Hook:
    MobArena:
        Enabled: false
        Disable-rewards-in-arena: true
```

## Configuration manual

```yaml
Global-settings:
    Money:
        Decimal-places: 2
```

+ **Decimal-places:** Decimal places in money

```yaml
Global-settings:
    Money:
        Item-drop:
            Enabled: false
            Item-name: "&c${amount}"
            Item-material: GOLD_INGOT
            Anyone-can-pick-it-up: true
```

Drop money reward on the ground. 

+ **Enabled:** Enable or disable Item drop function. Acceptable values: true or false
+ **Item-name:** Item name on the ground. You can use color codes in the name. The {amount} will be replaced with money amount.
+ **Item-material:** Item material. Use a Paper 26.2 item material such as `GOLD_INGOT` or `minecraft:gold_ingot`. [Material list](https://jd.papermc.io/paper/26.2/org/bukkit/Material.html)
+ **Anyone-can-pick-it-up:** Any player can pick up the money item, or only the killer

```yaml
Global-settings:
    Farming:
        Disable-spawner-farming: true
        Disable-spawner-egg-farming: false
```

+ **Disable-spawner-farming:** Disable reward when the entity spawned from spawner.
+ **Disable-spawner-egg-farming:** Disable reward when the entity spawned from spawner egg.

```yaml
Global-settings:
    General:
        Message-method: CHAT
        Allowed-gamemodes: SURVIVAL, CREATIVE
```

+ **Message-method:** Show reward messages in chat (`CHAT`), above the hotbar (`ACTION_BAR`), or disable them (`DISABLED`).
+ **Allowed-gamemodes:** KillerMoney disable functions in disallowed gamemodes. Acceptable values: "SURVIVAL", "CREATIVE" and "ADVENTURE".
You can set more gamemodes.

```yaml
Limit:
    Reset-time: 24
    Reached-limit-message: true
```

Reset money, item or command limits if you set up. If player reached the limit, then player will not get reward.

+ **Reset-time:** Reset time in hours.
+ **Reached-limit-message:** Player will get "reached limit" message at entity kill.

```yaml
Configurable-permissions:
    Money-multipliers:
        donator: 1.5
        vip: 2
        elite: 2.5
    Limit-multipliers:
        donator: 1.5
        vip: 2
        elite: 2.5
```

You can set permission based money and limit multiplier. These are example permission names, **you can change, delete or add more**.

+ Example 1: If you keep "donator" in Money-mutlipliers, then you must set this permission to permission group: **km.money.multiplier.donator**
+ Example 2: If you keep "elite" in Limit-multipliers, then you must set this permission to permission group: **km.moneylimit.multiplier.elite**
+ Example 3: If you created a new one then you must set permission to permission group: **km.money.multiplier.NAME** OR/AND **km.moneylimit.multiplier.NAME**

```yaml
Hook:
    MobArena:
        Enabled: false
        Disable-rewards-in-arena: true
```

+ [MobArena](https://dev.bukkit.org/projects/mobarena) is a great plugin. Players can fight with mobs in arena.
**Disabled-rewards-in-arena:** When true and players in MobArena, then they will not get rewards.
