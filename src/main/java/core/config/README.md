# Package org.team4153.core.config

The config holds some utilities for reading and writing configuration files.
We use the HOCON language, a superset of JSON.

The actual library we use is typesafe's ConfigFactory (com.typesafe.config.*).


## Config example
```hocon
// HOCON supports comments with // and /* */
motors = [
    {
        canId = 0   // the CAN id. only 0 to 62
        id = 0      /* the internal id used by the code.
                     * See the documentation at [[org.team4153.core.hardware.Hardware.ID]]
                     */
        name = "front-left drive"
        type = TalonFX
    }
    {
        canId = 1
        id = 1
        name = "front-left steer"
        type = TalonFX
        encoderOffset = ${encoders.1}
    }
    // ...
]

// generally, you would put objects like this in a separate file like
// encoders.conf so you can make a simple system to read all encoders and write
// to them at once.
encoders {
    "1" = 0.456
}
```
