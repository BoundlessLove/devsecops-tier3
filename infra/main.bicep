@description('Location for all resources')
param location string = resourceGroup().location

@description('ACR name (must be globally unique, lowercase)')
param acrName string = 'devsecopstier2acr'

@description('AKS cluster name')
param aksName string = 'devsecopstier2aks'

@description('Node count for AKS')
param nodeCount int = 2

@description('Kubernetes version (optional)')
@allowed([
  '1.29.0'
  '1.28.5'
  '1.27.9'
  '1.35.0'
])
param kubernetesVersion string = '1.35.0'

resource acr 'Microsoft.ContainerRegistry/registries@2023-01-01-preview' = {
  name: acrName
  location: location
  sku: {
    name: 'Basic'
  }
  properties: {
    adminUserEnabled: false
  }
}

resource aks 'Microsoft.ContainerService/managedClusters@2023-08-01' = {
  name: aksName
  location: location
  identity: {
    type: 'SystemAssigned'
  }
  properties: {
    kubernetesVersion: kubernetesVersion
    dnsPrefix: '${aksName}-dns'
    agentPoolProfiles: [
      {
        name: 'nodepool1'
        count: nodeCount
        vmSize: 'Standard_B2s'
        osType: 'Linux'
        type: 'VirtualMachineScaleSets'
        mode: 'System'
      }
    ]
    networkProfile: {
      networkPlugin: 'azure'
      loadBalancerSku: 'standard'
    }
  }
}

// Give AKS permission to pull from ACR
resource acrAksRole 'Microsoft.Authorization/roleAssignments@2022-04-01' = {
  name: guid(aks.id, acr.id, 'AcrPull')
  scope: acr
  properties: {
    roleDefinitionId: subscriptionResourceId(
      'Microsoft.Authorization/roleDefinitions',
      '7f951dda-4ed3-4680-a7ca-43fe172d538d' // AcrPull
    )
    principalId: aks.identity.principalId
    principalType: 'ServicePrincipal'
  }
} 
