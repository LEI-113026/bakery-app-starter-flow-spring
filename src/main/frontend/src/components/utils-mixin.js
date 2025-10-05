/**
 * A mixin to add scroll shadow effect to a component with a scrollable main area.
 * @param subclass
 * @returns {{readonly properties: {noScroll: {type: Boolean | BooleanConstructor, reflect: boolean, attribute: string}, _main: {attribute: boolean}}, new(): {_contentScroll(): void, firstUpdated(): void}, prototype: {_contentScroll(): void, firstUpdated(): void}}}
 * @constructor
 */
export const ScrollShadowMixin = (subclass) =>
  class extends subclass {
    static get properties() {
      return {
        noScroll: {
          type: Boolean,
          reflect: true,
          attribute: 'no-scroll',
        },
        _main: {
          attribute: false,
        },
      };
    }

    firstUpdated() {
      super.firstUpdated();

      this._main = this.shadowRoot.querySelector('#main');

      if (this._main) {
        this._main.addEventListener('scroll', () => this._contentScroll());
        this._contentScroll();
      }
    }

    _contentScroll() {
      if (this._main) {
        this.noScroll =
          this._main.scrollHeight - this._main.scrollTop ==
          this._main.clientHeight;
      }
    }
  };
