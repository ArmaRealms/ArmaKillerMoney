# ArmaKillerMoney

KillerMoney fork for Paper 26.2 and Java 25.

## Abilities of the plugin
+ Giving or charging a player by either a fix amount of money, or random between a max and minimum amount when they kill an entity
+ Charging the victim of a PVP
+ Fully customisable loot dropping when an entity die
+ Running a command of your choice when an entity die
+ Banning the ability to farm entities for money,if they were spawned by either eggs or spawners
+ Multiworld support for entities
+ Full modularity, the only thing you need to add to the config file is what you need!
+ Customisable messages
+ Permission support by each entity for a perfect rank system
+ Public API

## Commands
+ /km help
+ /km info
+ /km enable-messages <on or off>
+ /kmadmin limit reset
+ /kmadmin multiplier <set, get, cancel>
+ /kmadmin reload

## Permissions
| Permission | Description |
| ------------- | ------------- |
| km.admin | For admin commands: limit, multiplier, reload |
| km.bypass.moneylimit | Bypass money limit |
| km.bypass.itemlimit | Bypass item limit |
| km.bypass.commandlimit | Bypass command limit |
| km.bypass.cashtransferlimit | Bypass cash transfer limit |
| km.money.multiplier.NAME_FROM_CONFIG | Money multiplier |
| km.moneylimit.multiplier.NAME_FROM_CONFIG | Money limit multiplier |

## Installing

1. Use Paper 26.2 with Java 25.
2. Install Vault and a Vault-compatible economy provider to enable money rewards and transfers. MobArena is optional.
3. Run `./gradlew build` and copy `build/libs/KillerMoney.jar` into the server's `plugins` directory.
4. Start the server and edit [config.yml](docs/Config.md), [entities.yml](docs/Entities.md) and `lang.yml` in `plugins/KillerMoney`.

## Upgrading from KillerMoney 4.x

Back up `plugins/KillerMoney` and collect outstanding money items before replacing the JAR. Keep your customized configuration and player data. Rename the following keys in `entities.yml` and under `ENTITIES` in `lang.yml`.

| Previous key | Paper 26.2 key |
| ------------ | -------------- |
| `MUSHROOM_COW` | `MOOSHROOM` |
| `PIG_ZOMBIE` | `ZOMBIFIED_PIGLIN` |
| `SNOWMAN` | `SNOW_GOLEM` |

Remove `Config-version` and `Check-update` from `config.yml`. Use the [Paper item examples](docs/Entities.md) to update any custom drops that still use numeric item data values.

## API (WIP)

#### Events
+ KMCashTransferProcessorEvent
+ KMCCommandExecutionEvent
+ KMCItemDropEvent
+ KMEarnMoneyCashTransferDepositEvent
+ KMEarnMoneyDepositEvent
+ KMEarnMoneyPickedUpEvent
+ KMGlobalMultiplierChangeEvent
+ KMLimitReachedEvent
+ KMLoseMoneyCashTransferEvent
+ KMLoseMoneyEvent
+ KMMoneyItemDropEvent
+ KMMoneyProcessorEvent
+ KMSendActionBarMessageEvent
+ KMSendMessageEvent
