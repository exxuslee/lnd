docker network create --driver=bridge  --subnet=192.168.10.0/24 local_bridge

docker-compose up -d

docker-compose down

================================================================

bitcoin-cli getpeerinfo

bitcoin-cli addnode "testnet-seed.bitcoin.jonasschnelli.ch" "add"

================================================================

lncli --network=testnet getinfo

lncli --network=testnet listpeers
lncli --network=testnet walletbalance
lncli --network=testnet newaddress np2wkh

https://1ml.com/testnet/

lncli --network=testnet connect 03fea840ee807cc68ee2359669d3f00b638ba521a8f269a6ad7e618776af55010f@18.158.93.160:9735
lncli --network=testnet connect 03781444afe27babf724304c25370f195d9d955065d7894539ed7db5fe596badb8@138.197.57.50:19735
lncli --network=testnet connect 03c6083f63679afdcc8cc710d44ea9cce8ae99904c85c83ac06ee2e8a7fd4cd378@18.198.113.165:9735
lncli --network=testnet connect 0259d4116dff6ac1a33f8e8d909f151d375a9ba812c7a037b6d2a7372de8f68020@176.9.28.117:9735

==================================================================

curl -k --cacert /lnd/tls.cert --header "Grpc-Metadata-macaroon: $(xxd -ps -u -c 1000 /lnd/data/chain/bitcoin/testnet/admin.macaroon)" https://192.168.1.114:10009/v1/getinfo

$macaroon = "0201036C6E6402F801030A10CA1209542D999BC35287882B53A804DD1201301A160A0761646472657373120472656164120577726974651A130A04696E666F120472656164120577726974651A170A08696E766F69636573120472656164120577726974651A210A086D616361726F6F6E120867656E6572617465120472656164120577726974651A160A076D657373616765120472656164120577726974651A170A086F6666636861696E120472656164120577726974651A160A076F6E636861696E120472656164120577726974651A140A057065657273120472656164120577726974651A180A067369676E6572120867656E6572617465120472656164000006206DEB23562F2797DFB4438EB9570AE74A180D3241990064936B7BE515882FCA96"
$certPath = "C:\Temp\tls.cert"
$port = 8080
$lndHost = "172.18.0.3"

Write-Host "Cert Path: $certPath"
Write-Host "Macaroon: $macaroon"
Write-Host "Host: $lndHost"
Write-Host "Port: $port"

$curlCommand = "curl.exe -k --cacert '${certPath}' --header 'Grpc-Metadata-macaroon: ${macaroon}' 'https://${lndHost}:${port}/v1/getinfo'"
Invoke-Expression $curlCommand

grpcurl -insecure -H "Grpc-Metadata-macaroon: 0201036C6E6402F801030A10CA1209542D999BC35287882B53A804DD1201301A160A0761646472657373120472656164120577726974651A130A04696E666F120472656164120577726974651A170A08696E766F69636573120472656164120577726974651A210A086D616361726F6F6E120867656E6572617465120472656164120577726974651A160A076D657373616765120472656164120577726974651A170A086F6666636861696E120472656164120577726974651A160A076F6E636861696E120472656164120577726974651A140A057065657273120472656164120577726974651A180A067369676E6572120867656E6572617465120472656164000006206DEB23562F2797DFB4438EB9570AE74A180D3241990064936B7BE515882FCA96" -d '{}' 192.168.1.114:10009 lnrpc.Lightning.GetInfo

https://github.com/lightningnetwork/lnd/blob/master/docs/grpc/java.md



===================================================================

node js/rest

node js/jprc
