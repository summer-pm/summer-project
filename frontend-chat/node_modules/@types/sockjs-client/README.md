# Installation
> `npm install --save @types/sockjs-client`

# Summary
This package contains type definitions for sockjs-client (https://github.com/sockjs/sockjs-client).

# Details
Files were exported from https://github.com/DefinitelyTyped/DefinitelyTyped/tree/master/types/sockjs-client.
## [index.d.ts](https://github.com/DefinitelyTyped/DefinitelyTyped/tree/master/types/sockjs-client/index.d.ts)
````ts
// Type definitions for sockjs-client 1.5
// Project: https://github.com/sockjs/sockjs-client, http://sockjs.org
// Definitions by: Emil Ivanov <https://github.com/vladev>
//                 Alexander Rusakov <https://github.com/arusakov>
//                 BendingBender <https://github.com/BendingBender>
//                 Soner Köksal <https://github.com/renjfk>
//                 Alexander Putilov <https://github.com/PutilovAI>
// Definitions: https://github.com/DefinitelyTyped/DefinitelyTyped

export = SockJS;
export as namespace SockJS;

declare const SockJS: {
    new (url: string, _reserved?: any, options?: SockJS.Options): WebSocket;
    (url: string, _reserved?: any, options?: SockJS.Options): WebSocket;
    prototype: WebSocket;
    CONNECTING: SockJS.CONNECTING;
    OPEN: SockJS.OPEN;
    CLOSING: SockJS.CLOSING;
    CLOSED: SockJS.CLOSED;
};

declare namespace SockJS {
    type CONNECTING = 0;
    type OPEN = 1;
    type CLOSING = 2;
    type CLOSED = 3;

    type State = CONNECTING | OPEN | CLOSING | CLOSED;

    interface BaseEvent extends Event {
        type: string;
    }

    type OpenEvent = BaseEvent;

    interface CloseEvent extends BaseEvent {
        code: number;
        reason: string;
        wasClean: boolean;
    }

    interface MessageEvent extends BaseEvent {
        data: string;
    }

    type SessionGenerator = () => string;

    interface Options {
        server?: string | undefined;
        sessionId?: number | SessionGenerator | undefined;
        transports?: string | string[] | undefined;
        timeout?: number | undefined;
    }
}

````

### Additional Details
 * Last updated: Tue, 06 Jul 2021 16:35:05 GMT
 * Dependencies: none
 * Global values: `SockJS`

# Credits
These definitions were written by [Emil Ivanov](https://github.com/vladev), [Alexander Rusakov](https://github.com/arusakov), [BendingBender](https://github.com/BendingBender), [Soner Köksal](https://github.com/renjfk), and [Alexander Putilov](https://github.com/PutilovAI).
