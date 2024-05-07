package com.opencritic.navigation

abstract class Destination(val path: String)

abstract class Routed(
    destination: Destination,
    args: Map<String, Any>,
) : Route(
    destination.path
        .let {
            var path = it
            args.forEach { (argName, value) ->
                path = path.replace("{${argName}}", value.toString())
            }
            path
        }
)