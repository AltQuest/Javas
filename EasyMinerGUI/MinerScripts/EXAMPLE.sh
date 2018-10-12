#!/bin/bash
cd
cd /EXAMPLE/EXAMPLE/EXAMPLE/Claymore
export GPU_MAX_HEAP_SIZE=100
export GPU_USE_SYNC_OBJECTS=1
export GPU_MAX_ALLOC_PERCENT=100
export GPU_SINGLE_ALLOC_PERCENT=100
./ethdcrminer64 -epool eth-eu1.nanopool.org:9999 -ewal 0x95b39bDC4F63b5B093a62991Ad1aDca27C58B836.testapp1 -epsw x
