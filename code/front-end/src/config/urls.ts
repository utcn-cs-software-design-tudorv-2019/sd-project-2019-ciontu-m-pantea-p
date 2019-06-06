const baseEndpoint = '';

export const urls = {

  register: [baseEndpoint, 'members', 'register'].join('/'),
  login: [baseEndpoint, 'login'].join('/'),

  createNewConversation: [baseEndpoint, 'conversation', 'create'].join('/'),
  getConversations: [baseEndpoint, 'conversation', 'all'].join('/'),
  getConversationInfo: [baseEndpoint, 'conversation', 'info'].join('/'),

  getMessages: [baseEndpoint, 'conversation', 'messages'].join('/'),
  addMessage: [baseEndpoint, 'conversation', 'message'].join('/'),

};
