#!/bin/bash

set -e #stops the script if any command fails

aws --endpoint-url=http://localhost:4566 cloudformation deploy \
    --stack-name patient-management \
    --template-file "./cdk.out/localstack.template.json"

##aws --endpoint-url=http://localhost:4566 elbv2 describe-load-balancers \
##    --query "LoadBalancers[0].DNSName" --output text

## new part added as said by claude
aws --endpoint-url=http://localhost:4566 cloudformation describe-stacks \
    --stack-name patient-management \
    --query "Stacks[0].Outputs[?OutputKey=='APIGatewayLoadBalancerDNS'].OutputValue" \
    --output text